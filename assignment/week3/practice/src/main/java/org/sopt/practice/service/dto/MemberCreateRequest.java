package org.sopt.practice.service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.sopt.practice.common.dto.Constant;
import org.sopt.practice.domain.Part;

public record MemberCreateRequest(
        @NotNull(message = "이름은 필수 입력 값입니다.")
        @NotBlank(message = "이름은 공백을 제외하고 1자 이상이어야 합니다.")
        @Size(max = Constant.MAX_NAME_LENGTH, message = "이름은 20자를 넘을 수 없습니다.")
        String name,

        @NotNull(message = "파트는 필수 입력 값입니다.")
        Part part,

        @NotNull(message = "나이는 필수 입력 값입니다.")
        @Size(min = Constant.ONE, max = Constant.MAX_AGE, message = "유효한 나이 값을 입력해야 합니다.(1~500)")
        int age
) {
}
