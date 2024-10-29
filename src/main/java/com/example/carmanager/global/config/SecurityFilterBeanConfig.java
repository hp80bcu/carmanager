package com.example.carmanager.global.config;

import com.example.carmanager.global.filter.JsonLoginProcessFilter;
import com.example.carmanager.global.filter.JwtAuthorizationFilter;
import com.example.carmanager.global.filter.handler.JwtProviderHandler;
import com.example.carmanager.global.oauth2.util.TokenProvider;
import com.example.carmanager.user.repository.RefreshTokenRepository;
import com.example.carmanager.user.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;

@Configuration
@RequiredArgsConstructor
public class SecurityFilterBeanConfig {

    private final ObjectMapper objectMapper;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;


    @Bean
    public JsonLoginProcessFilter jsonLoginProcessFilter(JwtProviderHandler jwtProviderHandler) {
        JsonLoginProcessFilter jsonLoginProcessFilter = new JsonLoginProcessFilter(objectMapper, authenticationManager);
        jsonLoginProcessFilter.setAuthenticationSuccessHandler(jwtProviderHandler);
        return jsonLoginProcessFilter;
    }

    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilter() {
        return new JwtAuthorizationFilter(tokenProvider, userRepository, refreshTokenRepository);
    }

    @Bean
    public JwtProviderHandler jwtProviderHandler() {
        return new JwtProviderHandler(tokenProvider, userRepository);
    }
}