package co.kr.easylogin.easylogin.util;

import co.kr.easylogin.easylogin.kakao.domain.KakaoBizApp;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Base64;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.servlet.view.RedirectView;

@Service
@RequiredArgsConstructor
@Slf4j
public class RestUtil {

    private final RestClient restClient;

    public RedirectView resultSendForKakaoBizApp(KakaoBizApp kakaoBizApp, String kakaoUserInfo) {

        RedirectView redirectView = new RedirectView();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(kakaoUserInfo);
            String jsonString = objectMapper.writeValueAsString(jsonNode);

            // Base64 인코딩
            String base64Encoded = Base64.getEncoder().encodeToString(jsonString.getBytes());
            redirectView.setUrl(kakaoBizApp.getRequestUrl() + "?encode=" + base64Encoded);

        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }

        return redirectView;
    }
}
