package org.sopt.practice.exception;

import lombok.Getter;
import org.sopt.practice.common.ErrorMessage;

public class NotFoundException extends BusinessException {
    public NotFoundException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
