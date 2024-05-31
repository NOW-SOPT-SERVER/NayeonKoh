package org.sopt.practice.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public record UserJoinResponse(
        String accessToken,
        String refreshToken,
        @JsonIgnore
        String userId
) {

    public static UserJoinResponse of(
            String accessToken,
            String refreshToken,
            String userId
    ) {
        return new UserJoinResponse(accessToken, refreshToken, userId);
    }
}
