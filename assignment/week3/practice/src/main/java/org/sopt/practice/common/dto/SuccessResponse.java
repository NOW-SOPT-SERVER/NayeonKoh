package org.sopt.practice.common.dto;

import jakarta.validation.constraints.NotNull;
import org.sopt.practice.common.SuccessMessage;
import org.springframework.lang.Nullable;

public record SuccessResponse<T>(
        @NotNull int status,
        @NotNull String message,
        @Nullable T data
) {
    public static <T> SuccessResponse<T> of(final SuccessMessage successMessage, @Nullable final T data) {
        return new SuccessResponse<>(successMessage.getStatus(), successMessage.getMessage(), data);
    }
}
