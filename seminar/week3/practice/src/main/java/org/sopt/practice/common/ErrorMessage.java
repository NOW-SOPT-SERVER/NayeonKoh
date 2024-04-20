package org.sopt.practice.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorMessage {

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
