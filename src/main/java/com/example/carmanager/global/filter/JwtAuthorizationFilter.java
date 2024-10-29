package com.example.carmanager.global.filter;

import com.example.carmanager.global.oauth2.util.TokenProvider;
import com.example.carmanager.user.entity.CustomUser;
import com.example.carmanager.user.entity.User;
import com.example.carmanager.user.repository.RefreshTokenRepository;
import com.example.carmanager.user.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
@Component
@Slf4j
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    /**
     * accessToken 유효 -> authentication 저장
     * accessToken 만료
     *      refreshToken 유효 -> authentication 저장, accessToken 갱신
     *      refreshToken 만료 -> authentication 저장 X
     */

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, IOException {
        log.info("login 외 접근");
        tokenProvider.extractAccessToken(request)
                .filter(tokenProvider::isTokenValid)
                .ifPresentOrElse(
                        this::saveAuthentication,
                        () -> checkRefreshToken(request, response)
                );
        filterChain.doFilter(request, response);
    }

    private void saveAuthentication(String accessToken) {
        String email = tokenProvider.extractUserEmail(accessToken);
        CustomUser securityUser = new CustomUser(userRepository.findUserByEmail(email).get());
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(securityUser, null, securityUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private void checkRefreshToken(HttpServletRequest request, HttpServletResponse response) {
        Optional<String> refreshToken = tokenProvider.extractRefreshToken(request)
                .filter(tokenProvider::isTokenValid);

        if (refreshToken.isPresent()) {
            Long userId = refreshTokenRepository.findUserIdByRefreshToken(String.valueOf(refreshToken));
            User user = userRepository.findByUserId(userId);
            String accessToken = tokenProvider.createAccessToken(user.getEmail());
            tokenProvider.setAccessTokenInHeader(response, accessToken);
            saveAuthentication(accessToken);
        } else {
            doNotSaveAuthentication();
        }
    }

    private void doNotSaveAuthentication() {
    }
}