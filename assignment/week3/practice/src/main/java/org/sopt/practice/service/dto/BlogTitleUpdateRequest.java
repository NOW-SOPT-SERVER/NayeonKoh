package org.sopt.practice.service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.sopt.practice.common.dto.Constant;

public record BlogTitleUpdateRequest(
        @NotBlank(message = "블로그 제목은 필수 입력 값입니다.")
        @Size(max = Constant.MAX_TITLE_LENGTH, message = "블로그 제목은 200자를 넘을 수 없습니다.")
        String title
) {
}
