package org.sopt.karrot.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    // BAD REQUEST
    BAD_REQUEST(40000, HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),

    // ILLEGAL ARGUMENT
    ILLEGAL_ARGUMENT(40001, HttpStatus.BAD_REQUEST, "잘못된 인자입니다."),

    // NOT FOUND
    NOT_FOUND(40400, HttpStatus.NOT_FOUND, "해당 리소스를 찾을 수 없습니다."),
    NOT_FOUND_MEMBER(40401, HttpStatus.NOT_FOUND, "해당 회원을 찾을 수 없습니다."),

    // INTERNAL SERVER ERROR
    INTERNAL_SERVER_ERROR(50000, HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류입니다."),
    INTERNAL_SERVER_ERROR_BEOPJEONGDONG(50001, HttpStatus.INTERNAL_SERVER_ERROR, "법정동 정보를 가져오는 중 오류가 발생했습니다."),
    ;

    private final int status;
    private final HttpStatus httpStatus;
    private final String message;

    ErrorCode(final int status, final HttpStatus httpStatus, final String message) {
        this.status = status;
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
