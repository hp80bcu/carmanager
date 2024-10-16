package com.example.carmanager.config;

import com.example.carmanager.config.jwt.JwtTokenFilter;
import com.example.carmanager.config.jwt.TokenProvider;
import com.example.carmanager.config.oauth.OAuthFailureHandler;
import com.example.carmanager.config.oauth.OAuthSuccessHandler;
import com.example.carmanager.user.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor
@Configuration
public class WebOAuthSecurityConfig {
    private final OAuthSuccessHandler oauthSuccessHandler;
    private final OAuthFailureHandler oauthFailureHandler;
    private final CustomOAuth2UserService customOAuth2UserService;
    private final TokenProvider tokenProvider;

    @Bean
    public WebSecurityCustomizer configure() {
        return (web) -> web.ignoring()
                .requestMatchers("/img/**", "/css/**", "/js/**");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .logout(logout ->
                        logout
                                .logoutUrl("/users/logout")
                                .deleteCookies("refresh_token")
                                .logoutSuccessUrl("/")
                )
                .headers(headers ->
                        headers.frameOptions(
                                HeadersConfigurer.FrameOptionsConfig::disable).disable())
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
                .addFilterBefore(new JwtTokenFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/api/token", "/users/**", "/","/board/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/**").authenticated()
                                .anyRequest().authenticated()
                )
                .oauth2Login(oauth2Login ->
                        oauth2Login
                                .loginPage("/")
                                .userInfoEndpoint(userInfoEndpoint ->
                                        userInfoEndpoint.userService(customOAuth2UserService)
                                )
                                .successHandler(oauthSuccessHandler)
                                .failureHandler(oauthFailureHandler)
                )

                .exceptionHandling(exceptionHandling ->
                        exceptionHandling.defaultAuthenticationEntryPointFor(
                                new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED),
                                new AntPathRequestMatcher("/api/**")
                        )
                );
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
