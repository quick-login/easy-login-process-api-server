package co.kr.easylogin.easylogin.kakao.dto;

import co.kr.easylogin.easylogin.kakao.domain.KakaoBizApp;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * 카카오 토큰요청 Request DTO
 */
@Getter
@Slf4j
@ToString
public class KakaoAuthTokenRequest {
    private final String grant_type; //authorization_code로 고정
    private final String client_id;
    private final String redirect_uri;
    private final String code;

    @Builder
    private KakaoAuthTokenRequest(String client_id, String redirect_uri, String code) {
        this.grant_type = "authorization_code";
        this.client_id = client_id;
        this.redirect_uri = redirect_uri;
        this.code = code;
    }

    public static KakaoAuthTokenRequest of(KakaoBizApp kakaoBizApp, String code, String serverUrl) {
        return KakaoAuthTokenRequest.builder()
                                    .client_id(kakaoBizApp.getRestKey())
                                    .redirect_uri(serverUrl + "/api/v1/kakao/process/" + kakaoBizApp.getAppId())
                                    .code(code)
                                    .build();
    }
}
