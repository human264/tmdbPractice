package fast.campus.netplix3.advice;

import fast.campus.netplix3.controller.NetplixApiResponse;
import fast.campus.netplix3.exception.ErrorCode;
import fast.campus.netplix3.exception.UserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(UserException.class)
    protected NetplixApiResponse<?> handleUserException(UserException e) {
        log.error("error={}", e.getErrorCode(), e);
        return NetplixApiResponse.fail(e.getErrorCode(), e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    protected NetplixApiResponse<?> handleRuntimeException(RuntimeException e) {
        log.error("error={}", e.getMessage(), e);
        return NetplixApiResponse.fail(ErrorCode.DEFAULT_ERROR, e.getMessage());
    }
}
