package org.sopt.practice.service.dto;

import org.sopt.practice.domain.Member;
import org.sopt.practice.domain.Part;

public record MemberFindResponse(
        String name,
        Part part,
        int age
) {
    public static MemberFindResponse of(Member member) {
        return new MemberFindResponse(member.getName(), member.getPart(), member.getAge());
    }
}
