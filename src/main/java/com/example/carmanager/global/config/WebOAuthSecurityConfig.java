package com.example.carmanager.global.config;

import com.example.carmanager.global.OAuthAuthorizationRequestBasedOnCookieRepository;
import com.example.carmanager.global.filter.JsonLoginProcessFilter;
import com.example.carmanager.global.filter.JwtAuthorizationFilter;
import com.example.carmanager.global.filter.handler.OAuthSuccessHandler;
import com.example.carmanager.global.oauth2.CustomOAuth2UserService;
import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;


@EnableWebSecurity
@RequiredArgsConstructor
public class WebOAuthSecurityConfig {
    private final CustomOAuth2UserService customOAuth2UserService;
    private final OAuthSuccessHandler oauthSuccessHandler;
    private final OAuthAuthorizationRequestBasedOnCookieRepository oAuthAuthorizationRequestBasedOnCookieRepository;
    private final JsonLoginProcessFilter jsonLoginProcessFilter;
    private final JwtAuthorizationFilter jwtAuthorizationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(withDefaults()) // 기본 CORS 설정
                .httpBasic(httpBasic -> httpBasic.disable())
                .rememberMe(rememberMe -> rememberMe.disable())
                .headers(headers -> headers.disable())
                .formLogin(formLogin -> formLogin.disable())
                .requestCache(requestCache -> requestCache.disable())
                .logout(logout -> logout.disable())
                .exceptionHandling(withDefaults())

                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/api/token", "/users/**", "/","/board/**").permitAll() // 로그인, 회원가입 관련 API는 인증 없이 접근 가능
                                .requestMatchers(HttpMethod.POST, "/api/**").authenticated()
                                .anyRequest().authenticated() // 나머지 요청은 인증 필요
                )


                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/")
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
}
