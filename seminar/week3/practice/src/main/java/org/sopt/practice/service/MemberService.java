package org.sopt.practice.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.sopt.practice.domain.Member;
import org.sopt.practice.repository.MemberRepository;
import org.sopt.practice.service.dto.MemberCreateDto;
import org.sopt.practice.service.dto.MemberFindDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public String createMember(
            MemberCreateDto memberCreateDTO
    ) {
        Member member = memberRepository.save(Member.create(memberCreateDTO.name(), memberCreateDTO.part(), memberCreateDTO.age()));
        return member.getId().toString();
    }

    private Member findMemberById(
            final Long memberId
    ) {
        return memberRepository.findById(memberId).orElseThrow(() -> new EntityNotFoundException("Member not found"));
    }

    public MemberFindDto getMemberById(
            final Long memberId
    ) {
        return MemberFindDto.of(findMemberById(memberId));
    }

    @Transactional
    public void deleteMemberById(
            final Long memberId
    ) {
        Member member = findMemberById(memberId);
        memberRepository.delete(member);
    }
}
