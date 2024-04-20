package org.sopt.practice.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum SuccessMessage {

    BLOG_CREATE_SUCCESS(HttpStatus.CREATED.value(), "created blog successfully"),
    ;

    private final int status;
    private final String message;

    SuccessMessage(final int status, final String message) {
        this.status = status;
        this.message = message;
    }
}
