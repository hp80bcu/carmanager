package com.example.carmanager.global.filter.handler;

import com.example.carmanager.global.oauth2.model.TokenMapping;
import com.example.carmanager.global.oauth2.util.TokenProvider;
import com.example.carmanager.user.entity.CustomUser;
import com.example.carmanager.user.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtProviderHandler implements AuthenticationSuccessHandler {

    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        CustomUser securityUser = (CustomUser) authentication.getPrincipal();
        String email = securityUser.getEmail();

        TokenMapping token = tokenProvider.createToken(email);
        tokenProvider.sendBothToken(response, token.getAccessToken(), token.getRefreshToken());
    }
}
