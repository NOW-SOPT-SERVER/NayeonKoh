package org.sopt.karrot.controller;

import lombok.RequiredArgsConstructor;
import org.sopt.karrot.dto.request.MemberCreateDto;
import org.sopt.karrot.dto.common.ResponseDto;
import org.sopt.karrot.service.MemberService;
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
    public ResponseDto<?> createMember(@RequestBody final MemberCreateDto memberCreateDto) {
        memberService.createMember(memberCreateDto);
        return ResponseDto.success(null);
    }
}
