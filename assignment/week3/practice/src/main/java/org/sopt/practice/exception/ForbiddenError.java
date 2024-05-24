package org.sopt.practice.exception;

import org.sopt.practice.common.ErrorMessage;

public class ForbiddenError extends BusinessException {
    public ForbiddenError(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
