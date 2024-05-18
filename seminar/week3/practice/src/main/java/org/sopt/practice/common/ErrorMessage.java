package org.sopt.practice.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorMessage {
    // 400 BAD REQUEST
    LONGER_THAN_MAX_LENGTH(HttpStatus.BAD_REQUEST.value(), "longer than max length"),

    // 401 UNAUTHORIZED
    JWT_UNAUTHORIZED_EXCEPTION(HttpStatus.UNAUTHORIZED.value(), "사용자의 로그인 검증을 실패했습니다."),

    // 404 NOT FOUND
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "member not found"),
    BLOG_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "blog not found"),
    ;

    private int status;
    private String message;

    ErrorMessage(final int status, final String message) {
        this.status = status;
        this.message = message;
    }
}
