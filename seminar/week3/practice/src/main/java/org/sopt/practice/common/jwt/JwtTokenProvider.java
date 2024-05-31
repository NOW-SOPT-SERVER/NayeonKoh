package org.sopt.practice.common.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import java.util.Base64;
import java.util.Date;
import javax.crypto.SecretKey;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    private static final String USER_ID = "userId";
    private static final String BEARER_PREFIX = "Bearer ";

    private static final Long ACCESS_TOKEN_EXPIRATION_TIME = 24 * 60 * 60 * 1000L * 7;
    private static final Long REFRESH_TOKEN_EXPIRATION_TIME = 24 * 60 * 60 * 1000L * 14;

    @Value("${jwt.secret}")
    private String JWT_SECRET;

    /* token 생성 시 호출되는 메서드 */
    /* Authentication: UserAuthentication 객체
    * - princpal: userId
    * - credentials: null
    * - authorities: null
    *  */
    public String issueAccessToken(final Authentication authentication) {
        return generateAccessToken(authentication, ACCESS_TOKEN_EXPIRATION_TIME);
    }

    public String issueRefreshToken() {
        return generateRefreshToken(REFRESH_TOKEN_EXPIRATION_TIME);
    }

    /* 토큰 생성 로직
    - claim에 멤버 식별자로 사용할 userId 보관
    - 토큰 생성/만료 시간 설정

    Jwts.builder(): JWT 생성
    - TYPE: JWT로 설정
    - Claims: 토큰에 담을 claim 객체 설정
    - signWith: 서명 설정 및 암호화
    - compact: 토큰 생성
    */
    public String generateAccessToken(Authentication authentication, Long tokenExpirationTime) {
        final Date now = new Date();
        final Claims claims = Jwts.claims()
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenExpirationTime));      // 만료 시간

        claims.put(USER_ID, authentication.getPrincipal());

        return BEARER_PREFIX + Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE) // Header
                .setClaims(claims) // Claim
                .signWith(getSigningKey()) // Signature
                .compact();
    }

    public String generateRefreshToken(Long tokenExpirationTime) {
        final Date now = new Date();
        final Claims claims = Jwts.claims()
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenExpirationTime));      // 만료 시간

        return BEARER_PREFIX + Jwts.builder()
                .setClaims(claims)
                .signWith(getSigningKey())
                .compact();
    }

    /* 서명 생성
    * Base64 인코딩된 JWT_SECRET을 SecretKey로 변환
    *  */
    private SecretKey getSigningKey() {
        String encodedKey = Base64.getEncoder().encodeToString(JWT_SECRET.getBytes()); //SecretKey 통해 서명 생성
        return Keys.hmacShaKeyFor(encodedKey.getBytes());   //일반적으로 HMAC (Hash-based Message Authentication Code) 알고리즘 사용
    }

    /* 토큰 유효성 검사 */
    public JwtValidationType validateToken(String token) {
        try {
            final Claims claims = getBody(token);
            return JwtValidationType.VALID_JWT;
        } catch (MalformedJwtException ex) {
            return JwtValidationType.INVALID_JWT_TOKEN;
        } catch (ExpiredJwtException ex) {
            return JwtValidationType.EXPIRED_JWT_TOKEN;
        } catch (UnsupportedJwtException ex) {
            return JwtValidationType.UNSUPPORTED_JWT_TOKEN;
        } catch (IllegalArgumentException ex) {
            return JwtValidationType.EMPTY_JWT;
        }
    }

    /* 서명으로 토큰 파싱 -> claim 얻기 */
    private Claims getBody(final String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /* 토큰 claim에서 userId 조회 */
    public Long getUserFromJwt(String token) {
        Claims claims = getBody(token);
        return Long.valueOf(claims.get(USER_ID).toString());
    }
}
