package org.sopt.practice.service;

import lombok.RequiredArgsConstructor;
import org.sopt.practice.auth.UserAuthentication;
import org.sopt.practice.auth.redis.domain.Token;
import org.sopt.practice.auth.redis.repository.RedisTokenRepository;
import org.sopt.practice.common.jwt.JwtTokenProvider;
import org.sopt.practice.domain.Member;
import org.sopt.practice.repository.MemberRepository;
import org.sopt.practice.service.dto.MemberCreateDto;
import org.sopt.practice.service.dto.UserJoinResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisTokenRepository redisTokenRepository;

    @Transactional
    public UserJoinResponse signUp(
            MemberCreateDto memberCreate
    ) {
        Member member = memberRepository.save(
                Member.create(memberCreate.name(), memberCreate.part(), memberCreate.age())
        );
        Long memberId = member.getId();
        String accessToken = jwtTokenProvider.issueAccessToken(
                UserAuthentication.createUserAuthentication(memberId)
        );
        String refreshToken = jwtTokenProvider.issueRefreshToken();
        redisTokenRepository.save(Token.of(memberId, refreshToken));

        return UserJoinResponse.of(accessToken, refreshToken, memberId.toString());
    }

    @Transactional
    public UserJoinResponse signIn(
            String refreshToken
    ) {
        Token redisToken = redisTokenRepository.findByRefreshTokenOrElseThrow(refreshToken);
        String newAccessToken = jwtTokenProvider.issueAccessToken(
                UserAuthentication.createUserAuthentication(redisToken.getId())
        );

        return UserJoinResponse.of(newAccessToken, refreshToken, redisToken.getId().toString());
    }
}
