package co.kr.easylogin.easylogin.kakao.service;

import co.kr.easylogin.easylogin.error.BusinessException;
import co.kr.easylogin.easylogin.error.ErrorCode;
import co.kr.easylogin.easylogin.kakao.data.KakaoUserInfo;
import co.kr.easylogin.easylogin.kakao.domain.KakaoApp;
import co.kr.easylogin.easylogin.kakao.dto.KakaoAuthTokenRequest;
import co.kr.easylogin.easylogin.kakao.dto.KakaoAuthTokenResponse;
import co.kr.easylogin.easylogin.kakao.repository.KakaoAppRepository;
import co.kr.easylogin.easylogin.util.RestUtil;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;
import org.springframework.web.servlet.view.RedirectView;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class KakaoAuthService {

    private final KakaoAppRepository kakaoBizAppRepository;
    private final RestClient restClient;
    private final RestUtil restUtil;

    @Value("${kakao.server_url}")
    private String serverUrl;

    /**
     * 카카오 인가코드 받기 url 생성
     * GET https://kauth.kakao.com/oauth/authorize
     */
    public String createKakaoAuthorizeUrl(Long appId, String state) {
        StringBuilder sb = new StringBuilder();
        KakaoApp kakaoBizApp =
            kakaoBizAppRepository.findByAppId(appId)
                                 .orElseThrow(() -> new BusinessException(ErrorCode.UNDEFINED_KAKAO_APP_INFO));

        validateRemainCount(kakaoBizApp);

        StringBuilder ap = sb.append("https://kauth.kakao.com/oauth/authorize")
                                 .append("?client_id=").append(kakaoBizApp.getRestKey())
                                 .append("&redirect_uri=").append(serverUrl).append("/api/v1/kakao/process/")
                                 .append(kakaoBizApp.getAppId())
                                 .append("&response_type=code");

        if (state != null && !state.isEmpty()) {
            // state 값 존재시 인코딩하여 추가
            String encodedState = URLEncoder.encode(state, StandardCharsets.UTF_8);
            ap.append("&state=").append(encodedState);
        }
        return ap.toString();
    }

    /**
     * 소셜로그인 API 호출 잔여횟수 확인
     */
    private void validateRemainCount(KakaoApp kakaoBizApp) {
        Long remainCount = kakaoBizApp.getMember().getRemainCount();
        log.info("{}-{} : API 호출 잔여 횟수 : {}", kakaoBizApp.getAppName(), kakaoBizApp.getAppId(), remainCount);
        if (remainCount <= 0) {
            log.error("{}-{} : API 호출 잔여 횟수 부족", kakaoBizApp.getAppName(), kakaoBizApp.getAppId());
            throw new BusinessException(ErrorCode.USER_ENOUGH_REMAIN_COUNT);
        }
    }

    /**
     * 카카오 로그인 프로세스 진행
     */
    @Transactional
    public RedirectView kakaoAuthorizeProcess(Long appId, String code, String state) {
        KakaoApp kakaoBizApp =
            kakaoBizAppRepository.findByAppId(appId)
                                 .orElseThrow(() -> new BusinessException(ErrorCode.UNDEFINED_KAKAO_APP_INFO));

        validateRemainCount(kakaoBizApp);
        KakaoAuthTokenResponse kakaoAuthTokenResponse = kakaoAuthorizeGetToken(kakaoBizApp, code);

        KakaoUserInfo kakaoUserInfo = kakaoAuthorizeGetUserInfo(kakaoAuthTokenResponse);
        return restUtil.resultSendForKakaoBizApp(kakaoBizApp, kakaoUserInfo, state);
    }

    /**
     * 토큰 발급요청
     * POST https://kauth.kakao.com/oauth/token
     */
    private KakaoAuthTokenResponse kakaoAuthorizeGetToken(KakaoApp kakaoBizApp, String code) {
        KakaoAuthTokenRequest kakaoAuthTokenRequest = KakaoAuthTokenRequest.of(kakaoBizApp, code, serverUrl);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", kakaoAuthTokenRequest.getGrant_type());
        body.add("client_id", kakaoBizApp.getRestKey());
        body.add("redirect_uri", kakaoAuthTokenRequest.getRedirect_uri());
        body.add("code", code);

        return restClient.post()
                         .uri("https://kauth.kakao.com/oauth/token")
                         .header(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded;charset=utf-8")
                         .body(body)
                         .retrieve()
                         .body(KakaoAuthTokenResponse.class);
    }

    /**
     * 사용자 정보 가져오기
     * GET/POST https://kapi.kakao.com/v2/user/me
     */
    private KakaoUserInfo kakaoAuthorizeGetUserInfo(KakaoAuthTokenResponse kakaoAuthTokenResponse) {
        return restClient.get()
                         .uri("https://kapi.kakao.com/v2/user/me")
                         .header(HttpHeaders.AUTHORIZATION, "Bearer " + kakaoAuthTokenResponse.getAccess_token())
                         .header(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded;charset=utf-8")
                         .retrieve()
                         .body(KakaoUserInfo.class);
    }

    /**
     * 카카오 로그인 에러 체크
     */
    public void kakaoAuthErrorCheck(String error, String errorDescription) {
        log.error("error : {} - {}", error, errorDescription);
    }

}
