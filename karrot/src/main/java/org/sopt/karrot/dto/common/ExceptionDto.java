package org.sopt.karrot.dto.common;

import lombok.Getter;
import org.sopt.karrot.exception.ErrorCode;

@Getter
public class ExceptionDto {
    private final int status;
    private final String message;

    private ExceptionDto(final int status, final String message) {
        this.status = status;
        this.message = message;
    }

    public static ExceptionDto from(final ErrorCode errorCode) {
        return new ExceptionDto(errorCode.getStatus(), errorCode.getMessage());
    }
}
