package com.example.carmanager.global.config;

import com.example.carmanager.global.OAuthAuthorizationRequestBasedOnCookieRepository;
import com.example.carmanager.global.filter.JsonLoginProcessFilter;
import com.example.carmanager.global.filter.JwtAuthorizationFilter;
import com.example.carmanager.global.filter.handler.OAuthSuccessHandler;
import com.example.carmanager.global.oauth2.CustomOAuth2UserService;
import com.example.carmanager.global.oauth2.util.TokenProvider;
import com.example.carmanager.user.repository.RefreshTokenRepository;
import com.example.carmanager.user.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.Filter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;


@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
public class WebOAuthSecurityConfig {
    private final CustomOAuth2UserService customOAuth2UserService;
    private final OAuthSuccessHandler oauthSuccessHandler;
    private final OAuthAuthorizationRequestBasedOnCookieRepository oAuthAuthorizationRequestBasedOnCookieRepository;
    private final JwtAuthorizationFilter jwtAuthorizationFilter;
    private final JsonLoginProcessFilter jsonLoginProcessFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable()) // 기본 CORS 설정
                .httpBasic(httpBasic -> httpBasic.disable())
                .rememberMe(rememberMe -> rememberMe.disable())
                .headers(headers -> headers.disable())
                .formLogin(formLogin -> formLogin.disable())
                .requestCache(requestCache -> requestCache.disable())
                .logout(logout -> logout.disable())

                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers( "/users/**", "/", "/board/**").permitAll() // 인증 없이 접근 가능 경로
                                .requestMatchers(HttpMethod.POST, "/api/**").authenticated()
                                .anyRequest().authenticated() // 나머지 요청은 인증 필요
                )


                .oauth2Login(oauth2 -> oauth2
                        .authorizationEndpoint(auth -> auth
                                .authorizationRequestRepository(oAuthAuthorizationRequestBasedOnCookieRepository)
                        )
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(customOAuth2UserService)
                        )
                        .successHandler(oauthSuccessHandler)
                )

                .addFilterAfter(jsonLoginProcessFilter, OAuth2LoginAuthenticationFilter.class)
                .addFilterAfter(jwtAuthorizationFilter, JsonLoginProcessFilter.class);

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        // 정적 리소스 spring security 대상에서 제외
        return (web) ->
                web
                        .ignoring()
                        .requestMatchers(
                                PathRequest.toStaticResources().atCommonLocations()
                        );
    }
}
