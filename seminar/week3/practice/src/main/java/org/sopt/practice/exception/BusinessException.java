package org.sopt.practice.exception;

import lombok.Getter;
import org.sopt.practice.common.ErrorMessage;

@Getter
public class BusinessException extends RuntimeException {
    private ErrorMessage errorMessage;

    public BusinessException(final ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
        this.errorMessage = errorMessage;
    }
}
