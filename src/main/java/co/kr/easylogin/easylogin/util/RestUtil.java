package co.kr.easylogin.easylogin.util;

import co.kr.easylogin.easylogin.kakao.data.KakaoUserInfo;
import co.kr.easylogin.easylogin.kakao.domain.KakaoApp;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Base64;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

@Service
@RequiredArgsConstructor
@Slf4j
public class RestUtil {

    public RedirectView resultSendForKakaoBizApp(KakaoApp kakaoApp, KakaoUserInfo kakaoUserInfo, String state) {

        RedirectView redirectView = new RedirectView();
        try {
            kakaoUserInfo.setState(state);

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonString = objectMapper.writeValueAsString(kakaoUserInfo);
            System.out.println("jsonString = " + jsonString);
            // Base64 인코딩
            String base64Encoded = Base64.getEncoder().encodeToString(jsonString.getBytes());
            redirectView.setUrl(kakaoApp.getRedirectUrl() + "?encode=" + base64Encoded);

        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
        successKakaoLoginProcess(kakaoApp);
        return redirectView;
    }

    public void successKakaoLoginProcess(KakaoApp kakaoBizApp) {
        kakaoBizApp.getMember().useRemainCount();
        log.info("카카오 로그인 데이터 전송 성공 : {} - {} : API 호출 회수 차감 : {}", kakaoBizApp.getAppName(), kakaoBizApp.getAppId(),
                 kakaoBizApp.getMember().getRemainCount());
    }
}
