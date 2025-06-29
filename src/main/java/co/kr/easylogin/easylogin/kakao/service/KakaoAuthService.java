package co.kr.easylogin.easylogin.kakao.service;

import co.kr.easylogin.easylogin.kakao.domain.KakaoBizApp;
import co.kr.easylogin.easylogin.kakao.repository.KakaoBizAppRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class KakaoAuthService {

    private final KakaoBizAppRepository kakaoBizAppRepository;

    @Value("${kakao.redirect_base_url}")
    private String redirectBaseUrl;

    /**
     * 카카오 인가코드 받기 url 생성 https://kauth.kakao.com/oauth/authorize 생성
     */
    public String createKakaoAuthorizeUrl(Long appId) {
        StringBuilder sb = new StringBuilder();
        KakaoBizApp kakaoBizApp = kakaoBizAppRepository.findByAppId(appId)
                                 .orElseThrow(() -> new IllegalArgumentException("AppId 비즈앱에 등록된 아이디가 없음"));

        String kakaoAuthorizeUrl = sb.append("https://kauth.kakao.com/oauth/authorize")
                                     .append("?client_id=").append(kakaoBizApp.getRestKey())
                                     .append("&redirect_uri=").append(redirectBaseUrl).append("/api/v1/kakao/process/")
                                     .append(kakaoBizApp.getAppId())
                                     .append("&response_type=code")
                                     .toString();

        log.info("Kakao authorize url: {}", kakaoAuthorizeUrl);
        return kakaoAuthorizeUrl;
    }
}
