package org.sopt.karrot.exception;

import lombok.extern.slf4j.Slf4j;
import org.sopt.karrot.dto.common.ResponseDto;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {IllegalArgumentException.class, MethodArgumentTypeMismatchException.class})
    public ResponseDto<?> handleIllegalArgumentException(IllegalArgumentException e) {
        log.error("handle illegal argument exception: {}", e.getMessage());
        return ResponseDto.fail(e);
    }

    /* 직접 정의한 에러 발생 */
    @ExceptionHandler(CommonException.class)
    public ResponseDto<?> handleCustomException(CommonException e) {
        log.error("handle custom exception: {}", e.getMessage());
        return ResponseDto.fail(e);
    }

    /* 서버, DB 예외 */
    @ExceptionHandler(Exception.class)
    public ResponseDto<?> handleException(Exception e) {
        log.error("handle exception: {}", e.getMessage());
        e.printStackTrace();
        return ResponseDto.fail(new CommonException(ErrorCode.INTERNAL_SERVER_ERROR));
    }
}
