package org.sopt.practice.common.dto;

import org.sopt.practice.common.SuccessMessage;

public record SuccessStatusResponse(
        int status,
        String message
) {
    public static SuccessStatusResponse of(final SuccessMessage successMessage) {
        return new SuccessStatusResponse(successMessage.getStatus(), successMessage.getMessage());
    }
}
