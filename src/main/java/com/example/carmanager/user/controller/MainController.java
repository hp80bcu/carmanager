package com.example.carmanager.user.controller;

import com.example.carmanager.global.oauth2.util.TokenProvider;
import com.example.carmanager.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MainController {
    private final UserService userService;
    private final TokenProvider tokenProvider;

    @GetMapping("/")
    public String Main(Authentication authentication) {
        if(authentication == null) {
            log.info("인증되지 않은 사용자 접속");
        } else {
            log.info("authentication.getName() : " + authentication.getName());
        }
        return "main";  // 인증되지 않은 경우에도 메인 페이지로
    }
}
