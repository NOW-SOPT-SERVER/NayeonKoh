package org.sopt.practice.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum SuccessMessage {
    /* 200 OK */
    OK(HttpStatus.OK.value(), "success"),

    /* 201 Created */
    MEMBER_CREATED_SUCCESS(HttpStatus.CREATED.value(), "created member successfully"),
    BLOG_CREATE_SUCCESS(HttpStatus.CREATED.value(), "created blog successfully"),
    ;

    private final int status;
    private final String message;

    SuccessMessage(final int status, final String message) {
        this.status = status;
        this.message = message;
    }
}
