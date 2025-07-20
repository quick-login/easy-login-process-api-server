package co.kr.easylogin.easylogin.error;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClient;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionControllerAdvice {

    private final RestClient restClient;

    @Value("${slack.url}")
    private String slackUrl;

    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException exception) {
        ErrorCode errorCode = exception.getErrorCode();
        ErrorResponse errorResponse = new ErrorResponse(errorCode.getCode(), errorCode.getMessage());
        sendSlack(errorResponse, exception);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        ErrorCode errorCode = ErrorCode.UNDEFINED_EASY_LOGIN_SERVER_ERROR;
        ErrorResponse errorResponse = new ErrorResponse(errorCode.getCode(), errorCode.getMessage());
        sendSlack(errorResponse, exception);
        log.error("Undefined EasyLogin Server Error", exception);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 슬랙 전송
     */
    public void sendSlack(ErrorResponse errorResponse, Exception exception) {
        String text = "이지로그인 소셜로그인 프로세싱 서버에서 오류 발생\n"
                      + "오류코드 : " + errorResponse.getErrorCode()
                      + "\n메시지 : " + errorResponse.getErrorMessage()
                      + "\n상세 익셉션 : " + Arrays.toString(exception.getStackTrace());

        Map<String, String> payload = new HashMap<>();
        payload.put("text", text);

        restClient.post()
                  .uri(slackUrl)
                  .body(payload)
                  .retrieve()
                  .body(String.class);
    }
}
