package org.sopt.practice.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.sopt.practice.common.ErrorMessage;
import org.sopt.practice.domain.Member;
import org.sopt.practice.exception.NotFoundException;
import org.sopt.practice.repository.MemberRepository;
import org.sopt.practice.service.dto.MemberCreateRequest;
import org.sopt.practice.service.dto.MemberFindResponse;
import org.sopt.practice.service.dto.MemberFindAllResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public String createMember(MemberCreateRequest memberCreateDTO) {
        Member member = memberRepository.save(
                Member.create(memberCreateDTO.name(), memberCreateDTO.part(), memberCreateDTO.age()));
        return member.getId().toString();
    }

    public MemberFindResponse getMemberById(final Long memberId) {
        return MemberFindResponse.of(findMemberById(memberId));
    }

    @Transactional
    public void deleteMemberById(final Long memberId) {
        Member member = findMemberById(memberId);
        memberRepository.delete(member);
    }

    public List<MemberFindAllResponse> getMembers() {
        return MemberFindAllResponse.listOf(memberRepository.findAll());
    }

    /* private -> protected로 다른 서비스 레이어에서 호출할 수 있도록 수정 */
    protected Member findMemberById(final Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(() -> new NotFoundException(ErrorMessage.MEMBER_NOT_FOUND));
    }
}
