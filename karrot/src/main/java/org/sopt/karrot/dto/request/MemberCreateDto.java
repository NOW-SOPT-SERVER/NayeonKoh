package org.sopt.karrot.dto.request;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NonNull;

public record MemberCreateDto(
        @NonNull
        @Size(min = 2, max = 20)
        String name
) {
}
