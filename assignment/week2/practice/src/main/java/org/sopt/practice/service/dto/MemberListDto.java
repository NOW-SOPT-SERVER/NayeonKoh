package org.sopt.practice.service.dto;

import java.util.List;
import org.sopt.practice.domain.Member;

public record MemberListDto(
        List<Member> members
) {

    public static MemberListDto of(final List<Member> members) {
        return new MemberListDto(members);
    }
}
