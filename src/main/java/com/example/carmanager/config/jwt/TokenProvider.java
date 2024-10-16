package com.example.carmanager.config.jwt;

import com.example.carmanager.user.entity.RefreshToken;
import com.example.carmanager.user.repository.RefreshTokenRepository;
import com.example.carmanager.user.service.UserDetailsServiceImpl;
import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class TokenProvider {
    private final JwtProperties properties;
    private final UserDetailsServiceImpl userDetailsService;
    private final RefreshTokenRepository refreshTokenRepository;

    private static final long ACCESS_TIME =  60 * 1000L;
    private static final long REFRESH_TIME =  2 * 60 * 1000L;
    public static final String ACCESS_TOKEN = "Access_Token";
    public static final String REFRESH_TOKEN = "Refresh_Token";

    // header 토큰을 가져오는 기능
    public String getHeaderToken(HttpServletRequest request, String type) {
        return type.equals("Access") ? request.getHeader(ACCESS_TOKEN) :request.getHeader(REFRESH_TOKEN);
    }

    public JwtTokenDTO createAllToken(long userId) {
        return new JwtTokenDTO(createToken(userId, "Access"), createToken(userId, "Refresh"));
    }

    public String createToken(long userId, String type) {
        Date date = new Date();

        long time = type.equals("Access") ? ACCESS_TIME : REFRESH_TIME;

        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .setExpiration(new Date(date.getTime() + time))
                .setIssuedAt(date)
                .signWith(SignatureAlgorithm.HS256, properties.getSecretKey())
                .compact();
    }
    // 토큰 검증
    public Boolean tokenValidation(String token) {
        try {
            Jwts.parser().setSigningKey(properties.getSecretKey()).parseClaimsJws(token);
            return true;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return false;
        }
    }

    // refreshToken 토큰 검증
    // db에 저장되어 있는 token과 비교
    // db에 저장한다는 것이 jwt token을 사용한다는 강점을 상쇄시킨다.
    // db 보다는 redis를 사용하는 것이 더욱 좋다. (in-memory db기 때문에 조회속도가 빠르고 주기적으로 삭제하는 기능이 기본적으로 존재합니다.)
    public Boolean refreshTokenValidation(String token) {

        // 1차 토큰 검증
        if(!tokenValidation(token)) return false;

        // DB에 저장한 토큰 비교
        Optional<RefreshToken> refreshToken = refreshTokenRepository.findByUserId(getUserIdFromToken(token));

        return refreshToken.isPresent() && token.equals(refreshToken.get().getRefreshToken());
    }

    // 인증 객체 생성
    public Authentication createAuthentication(String email) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        // spring security 내에서 가지고 있는 객체입니다. (UsernamePasswordAuthenticationToken)
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }


    public Long getUserIdFromToken(String token){
        return Long.valueOf(Jwts.parser().setSigningKey(properties.getSecretKey()).parseClaimsJws(token).getBody().getSubject());
    }

    // 어세스 토큰 헤더 설정
    public void setHeaderAccessToken(HttpServletResponse response, String accessToken) {
        response.setHeader("Access_Token", accessToken);
    }

    // 리프레시 토큰 헤더 설정
    public void setHeaderRefreshToken(HttpServletResponse response, String refreshToken) {
        response.setHeader("Refresh_Token", refreshToken);
    }
}