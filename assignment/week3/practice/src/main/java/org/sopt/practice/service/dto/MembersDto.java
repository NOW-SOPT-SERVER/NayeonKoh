package org.sopt.practice.service.dto;

import java.util.List;
import org.sopt.practice.domain.Member;
import org.sopt.practice.domain.Part;

public record MembersDto(Long id, String name, Part part) {

    public static List<MembersDto> listOf(
            final List<Member> members
    ) {
        return members
                .stream()
                .map(member -> new MembersDto(member.getId(), member.getName(), member.getPart()))
                .toList();
    }
}
