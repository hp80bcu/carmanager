package com.example.carmanager.global.config;

import com.example.carmanager.config.jwt.*;
import com.example.carmanager.global.OAuthFailureHandler;
import com.example.carmanager.global.OAuthSuccessHandler;
import com.example.carmanager.global.oauth2.util.TokenProvider;
import com.example.carmanager.global.oauth2.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.SessionManagementFilter;
import org.springframework.web.filter.CorsFilter;


@EnableWebSecurity
@RequiredArgsConstructor
public class WebOAuthSecurityConfig {

    private final TokenProvider tokenProvider;
    private final CorsFilter corsFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final JwtSecurityConfig jwtSecurityConfig;
    private final CustomOAuth2UserService customOAuth2UserService;
    private final OAuthSuccessHandler oauthSuccessHandler;
    private final OAuthFailureHandler oauthFailureHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // CSRF 설정 비활성화 (JWT를 사용할 것이므로)
        http.csrf(AbstractHttpConfigurer::disable)
                // CORS 필터 적용
                .addFilterBefore(corsFilter, SessionManagementFilter.class)

                // 예외 처리 핸들러 설정 (인증 실패 및 권한 부족 처리)
                .exceptionHandling(exceptionHandling ->
                        exceptionHandling
                                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                                .accessDeniedHandler(jwtAccessDeniedHandler)
                )

                // 세션 관리 정책 설정 (JWT 사용하므로 세션을 stateless로 설정)
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                // 인증 및 권한 관련 요청 처리
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/api/token", "/users/**", "/","/board/**").permitAll() // 로그인, 회원가입 관련 API는 인증 없이 접근 가능
                                .requestMatchers(HttpMethod.POST, "/api/**").authenticated()
                                .anyRequest().authenticated() // 나머지 요청은 인증 필요
                )

                // OAuth2 설정 추가
                .oauth2Login(oauth2 ->
                        oauth2
                                .loginPage("/")
                                .userInfoEndpoint(userInfo ->
                                        userInfo.userService(customOAuth2UserService)
                                )
                                .successHandler(oauthSuccessHandler)
                )
                .logout(logout ->
                        logout
                                .logoutUrl("/users/logout")
                                .deleteCookies("refresh_token")
                                .logoutSuccessUrl("/")
                );

        // JWT 필터 추가
        jwtSecurityConfig.configure(http);

        return http.build();
    }
}
