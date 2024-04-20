package org.sopt.practice.service.dto;

import java.util.ArrayList;
import java.util.List;
import org.sopt.practice.domain.Member;
import org.sopt.practice.domain.Part;

public record MemberListDto(
        List<MemberDto> members
) {

    public static MemberListDto of(final List<Member> members) {
        return new MemberListDto(new ArrayList<>(members.stream()
                .map(MemberDto::of)
                .toList()));
    }


    private record MemberDto(
            Long id,
            String name,
            Part part,
            int age
    ) {

        public static MemberDto of(final Member member) {
            return new MemberDto(member.getId(), member.getName(), member.getPart(), member.getAge());
        }
    }
}

