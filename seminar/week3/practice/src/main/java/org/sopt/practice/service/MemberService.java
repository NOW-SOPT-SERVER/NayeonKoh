package org.sopt.practice.service;

import lombok.RequiredArgsConstructor;
import org.sopt.practice.common.ErrorMessage;
import org.sopt.practice.domain.Member;
import org.sopt.practice.exception.NotFoundException;
import org.sopt.practice.repository.MemberRepository;
import org.sopt.practice.service.dto.MemberFindDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /* private -> protected로 다른 서비스 레이어에서 호출할 수 있도록 수정 */
    protected Member findMemberById(final Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(() -> new NotFoundException(ErrorMessage.MEMBER_NOT_FOUND));
    }

    public MemberFindDto getMemberById(final Long memberId) {
        return MemberFindDto.of(findMemberById(memberId));
    }

    @Transactional
    public void deleteMemberById(final Long memberId) {
        Member member = findMemberById(memberId);
        memberRepository.delete(member);
    }

}
