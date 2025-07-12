package co.kr.easylogin.easylogin.error;

import lombok.Getter;

@Getter
public class ErrorResponse {

    private final String errorCode;
    private final String errorMessage;

    public ErrorResponse(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
