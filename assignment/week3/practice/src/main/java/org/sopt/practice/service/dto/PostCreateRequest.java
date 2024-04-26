package org.sopt.practice.service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.sopt.practice.common.dto.Constant;

public record PostCreateRequest(
        @NotBlank(message = "게시글 제목은 필수 입력 값입니다.")
        @Size(max = Constant.MAX_TITLE_LENGTH, message = "게시글 제목은 200자를 넘을 수 없습니다.")
        String title,

        @NotBlank(message = "게시글 내용은 필수 입력 값입니다.")
        @Size(max = Constant.MAX_CONTENT_LENGTH, message = "게시글 내용은 2MB를 넘을 수 없습니다.")
        String content,

        @Positive(message = "블로그 식별자는 0보다 커야 합니다.")
        @NotNull(message = "블로그 식별자는 필수 입력 값입니다.")
        Long blogId
) {
}
