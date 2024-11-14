package com.example.carmanager.user.controller;

import com.example.carmanager.config.jwt.TokenProvider;
import com.example.carmanager.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MainController {
    private final UserService userService;
    private final TokenProvider tokenProvider;

    @GetMapping("/")
    public String Main(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            // Authentication의 principal이 OAuth2User인 경우
            if (authentication.getPrincipal() instanceof OAuth2User) {
                OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
                String username = oAuth2User.getName();  // 사용자 이름 가져오기
                log.info("인증된 사용자 이름: " + username);
                return "main";  // 메인 페이지로 이동
            } else {
                log.info("OAuth2 인증 사용자가 아닙니다.");
            }
        }
        log.info("인증 정보가 없습니다!");
        return "main";  // 인증되지 않은 경우에도 메인 페이지로
    }
}
