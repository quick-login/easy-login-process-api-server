package co.kr.easylogin.easylogin.error;

import lombok.Getter;

@Getter
public enum ErrorCode {
    //U- 사용자 에러
    USER_ENOUGH_REMAIN_COUNT("U1001", "API 호출 잔여 횟수가 남아있지 않습니다."),

    //K- 카카오 에러
//    K1000("K1000")

    //N- 네이버 에러

    //G- 구글 에러

    //E- 이지로그인 서버 에러
    EASY_LOGIN_KAKAO_USER_INFO_JSON_PARSING_ERROR("E5001", "카카오 사용자 정보 JSON 파싱 오류");

    private final String code;
    private final String message;


    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
