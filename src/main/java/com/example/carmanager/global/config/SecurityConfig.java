package com.example.carmanager.global.config;

import com.example.carmanager.global.filter.JwtAuthorizationFilter;
import com.example.carmanager.global.oauth2.util.TokenProvider;
import com.example.carmanager.global.OAuthFailureHandler;
import com.example.carmanager.global.filter.handler.OAuthSuccessHandler;
import com.example.carmanager.global.oauth2.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity //@Configuration 어노테이션 포함 및 보안 기능 활성화
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthorizationFilter jwtTokenFilter;
    private final TokenProvider tokenProvider;
    private final OAuthSuccessHandler oauthSuccessHandler;
    private final OAuthFailureHandler oauthFailureHandler;
    private final CustomOAuth2UserService customOAuth2UserService;
    //Http Security를 설정한다.
    @Bean
    public WebSecurityCustomizer configure() {
        return (web) -> web.ignoring()
                .requestMatchers("/img/**", "/css/**", "/js/**");
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeRequest ->
                        authorizeRequest
                                .requestMatchers(
                                        AntPathRequestMatcher.antMatcher("/api/**")
                                ).permitAll()
                                .requestMatchers(
                                        AntPathRequestMatcher.antMatcher("/users/**")
                                ).permitAll()
                )
                .headers(
                        headersConfiguer ->
                                headersConfiguer
                                        .frameOptions(
                                                HeadersConfigurer.FrameOptionsConfig::sameOrigin
                                        )
                );
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
