package org.sopt.practice.controller;

import lombok.RequiredArgsConstructor;
import org.sopt.practice.service.AuthService;
import org.sopt.practice.service.dto.MemberCreateDto;
import org.sopt.practice.service.dto.UserJoinResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping
    public ResponseEntity<UserJoinResponse> signUp(
            @RequestBody MemberCreateDto memberCreate
    ) {
        UserJoinResponse userJoinResponse = authService.signUp(memberCreate);
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Location", userJoinResponse.userId())
                .body(
                        userJoinResponse
                );
    }

    @PostMapping("/reissue")
    public ResponseEntity<UserJoinResponse> signIn(
            @RequestHeader("X-Refresh-Token") final String refreshToken
    ) {
        UserJoinResponse userJoinResponse = authService.signIn(refreshToken);
        return ResponseEntity.ok(userJoinResponse);
    }
}
