package org.sopt.practice.service.dto;

import jakarta.validation.constraints.Size;

public record BlogTitleUpdateRequest(
        @Size(max = 200, message = "블로그 제목은 200자를 넘을 수 없습니다.")
        String title
) {
}
