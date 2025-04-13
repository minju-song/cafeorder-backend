package com.minju.cafeOrder.core.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.minju.cafeOrder.user.dto.UserRequest;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Slf4j
@Component
public class JwtProvider {

    @Autowired
    CustomUserDetailsService userDetailsService;

    // 30분
    public static final int ACCESS_EXP_SEC = 60 * 300;

    // refresh 토큰 만료 시간 = 30일
    public static final int REFRESH_EXP_SEC = 60 * 60 * 24 * 30;

    // 토큰 접두사
    public static final String TOKEN_PREFIX = "Bearer ";
    // 요청 헤더 이름
    public static final String HEADER = "Authorization";
    // 비밀키
    private static final String secret = System.getenv("JWT_SECRET");

    public String createAccessToken(String email) {

        LocalDateTime now = LocalDateTime.now();
        // 만료 시간 계산
        LocalDateTime expired = now.plusSeconds(ACCESS_EXP_SEC);

        String jwt = JWT.create()
                .withExpiresAt(Timestamp.valueOf(expired)) // 만료 시간 설정
                .withClaim("email", email) // 사용자 ID 설정
                .sign(Algorithm.HMAC512(secret));
        return jwt;
    }

    public String createRefreshToken(String email) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expired = now.plusSeconds(REFRESH_EXP_SEC);
        return JWT.create()
                .withExpiresAt(Timestamp.valueOf(expired))
                .withClaim("email", email)
                .sign(Algorithm.HMAC512(secret));
    }

    private static DecodedJWT verify(String jwt) {

        // 접두사 제거
        return JWT.require(Algorithm.HMAC512(secret))
                .build()
                .verify(jwt);
    }

    public Authentication getAuthentication(String token) {
        // 토큰에서 사용자 이메일 꺼냄
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(secret))
                .build()
                .verify(token); // 토큰 유효성 검증 + 디코딩

        String email = decodedJWT.getClaim("email").asString();

        // 이메일로 UserDetails 객체 조회
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);

        // Security에서 사용할 인증 객체 반환
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

}
