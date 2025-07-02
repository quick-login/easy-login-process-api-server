package co.kr.easylogin.easylogin.error;

import lombok.Getter;

@Getter
public enum ErrorCode {
    USER_ENOUGH_REMAIN_COUNT("U1001", "API 호출 잔여 횟수가 남아있지 않습니다.");

    private final String code;
    private final String message;

    //U- 사용자 에러

    //K- 카카오 에러
//    K1000("K1000")

    //N- 네이버 에러

    //G- 구글 에러


    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
