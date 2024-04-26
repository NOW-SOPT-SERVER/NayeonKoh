package org.sopt.practice.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorMessage {
    // 400 BAD REQUEST
    LONGER_THAN_MAX_LENGTH(HttpStatus.BAD_REQUEST.value(), "longer than max length"),

    // 404 NOT FOUND
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "member not found"),
    BLOG_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "blog not found"),
    POST_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "post not found"),
    ;

    private final int status;
    private final String message;

    ErrorMessage(final int status, final String message) {
        this.status = status;
        this.message = message;
    }
}
