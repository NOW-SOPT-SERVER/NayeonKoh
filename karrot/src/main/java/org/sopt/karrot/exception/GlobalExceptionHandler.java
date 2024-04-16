package org.sopt.karrot.exception;

import org.sopt.karrot.dto.common.ResponseDto;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /* 직접 정의한 에러 발생 */
    @ExceptionHandler(CommonException.class)
    public ResponseDto<?> handleApiException(CommonException e) {
        return ResponseDto.fail(e);
    }

    /*  */
    @ExceptionHandler(Exception.class)
    public ResponseDto<?> handleException(Exception e) {
        e.printStackTrace();
        return ResponseDto.fail(new CommonException(ErrorCode.INTERNAL_SERVER_ERROR));
    }
}
