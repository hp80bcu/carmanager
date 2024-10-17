package com.example.carmanager.global.filter;

import com.example.carmanager.global.oauth2.util.TokenProvider;
import com.example.carmanager.global.oauth2.model.SecurityUser;
import com.example.carmanager.user.entity.User;
import com.example.carmanager.user.repository.RefreshTokenRepository;
import com.example.carmanager.user.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
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
        System.out.println("login 외 접근");
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
        SecurityUser securityUser = new SecurityUser(userRepository.findUserByEmail(email).get());
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(securityUser, null, securityUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private void checkRefreshToken(HttpServletRequest request, HttpServletResponse response) {
        Optional<String> refreshToken = tokenProvider.extractRefreshToken(request)
                .filter(tokenProvider::isTokenValid);

        if (refreshToken.isPresent()) {
            User user = userRepository.findUserByRefreshToken(refreshToken.get())
                    .orElseThrow(IllegalArgumentException::new);
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