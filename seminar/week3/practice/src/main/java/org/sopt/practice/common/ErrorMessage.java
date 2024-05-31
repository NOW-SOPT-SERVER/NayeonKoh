package org.sopt.practice.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorMessage {
    // 400 BAD REQUEST
    LONGER_THAN_MAX_LENGTH(HttpStatus.BAD_REQUEST.value(), "최대 길이보다 깁니다."),

    // 401 UNAUTHORIZED
    JWT_UNAUTHORIZED_EXCEPTION(HttpStatus.UNAUTHORIZED.value(), "사용자의 로그인 검증을 실패했습니다."),

    // 404 NOT FOUND
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "해당하는 사용자가 없습니다."),
    BLOG_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "해당하는 블로그가 없습니다."),
    REFRESH_TOKEN_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "해당하는 리프레시 토큰이 없습니다."),
    ;

    private final int status;
    private final String message;

    ErrorMessage(final int status, final String message) {
        this.status = status;
        this.message = message;
    }
}
