package org.sopt.practice.controller;

import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.sopt.practice.common.SuccessMessage;
import org.sopt.practice.common.dto.SuccessResponse;
import org.sopt.practice.service.MemberService;
import org.sopt.practice.service.dto.MemberCreateRequest;
import org.sopt.practice.service.dto.MemberFindResponse;
import org.sopt.practice.service.dto.MemberFindAllResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public SuccessResponse<?> createMember(
            @RequestBody MemberCreateRequest memberCreateDTO,
            HttpServletResponse response
    ) {
        String createdMemberId = memberService.createMember(memberCreateDTO);
        /*
         Location header field에 새로운 리소스에 대한 uri 엔드포인트 설정
         Member-ID field에 생성된 member의 id 설정
        */
        response.setHeader("Location", "/api/v1/members/" + createdMemberId);
        response.setHeader("Member-ID", createdMemberId);
        return SuccessResponse.of(SuccessMessage.MEMBER_CREATED_SUCCESS, null);
    }

    @GetMapping("/{memberId}")
    public SuccessResponse<MemberFindResponse> findMemberById(
            @PathVariable final Long memberId
    ) {
        return SuccessResponse.of(SuccessMessage.OK, memberService.getMemberById(memberId));
    }

    @DeleteMapping("/{memberId}")
    public SuccessResponse<?> deleteMemberById(
            @PathVariable final Long memberId
    ) {
        memberService.deleteMemberById(memberId);
        return SuccessResponse.of(SuccessMessage.OK, null);
    }

    @GetMapping
    public SuccessResponse<List<MemberFindAllResponse>> getMembers() {
        return SuccessResponse.of(SuccessMessage.OK, memberService.getMembers());
    }
 }
