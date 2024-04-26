package org.sopt.practice.service.dto;

import org.sopt.practice.domain.Part;

public record MemberCreateRequest(
        String name,
        Part part,
        int age
) {
}
