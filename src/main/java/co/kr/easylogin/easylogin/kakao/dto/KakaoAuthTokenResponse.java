package co.kr.easylogin.easylogin.kakao.dto;

import lombok.Getter;

/**
 * 카카오 토큰요청 Response DTO
 */
@Getter
public class KakaoAuthTokenResponse {
    private String token_type; // 토큰타입, bearer로 고정

    private String access_token; // 사용자 액세스 토큰 값

    private Long expires_in;  // 액세스 토큰의 만료시간(초)

    private String refresh_token; // 사용자 리프레시 토큰 값

    private Long refresh_token_expires_in; // 리프레시 토큰 만료시간(초)
}
