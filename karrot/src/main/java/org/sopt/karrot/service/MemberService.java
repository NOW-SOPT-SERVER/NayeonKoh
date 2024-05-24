package org.sopt.karrot.service;

import lombok.RequiredArgsConstructor;
import org.sopt.karrot.domain.Member;
import org.sopt.karrot.dto.request.MemberCreateDto;
import org.sopt.karrot.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public void createMember(final MemberCreateDto memberCreateDto) {
        Member member = Member.from(memberCreateDto);
        memberRepository.save(member);
    }
}
