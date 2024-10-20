package com.example.carmanager.global.oauth2.util;

import com.example.carmanager.user.entity.CustomUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthenticationLogger {

    public void logAuthenticationDetails(Authentication authentication) {
        if(authentication == null) {
            log.info("인증되지 않은 사용자 접속");
        } else {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                CustomUser userDetails = (CustomUser) principal; // UserDetails 구현체로 캐스팅
                log.info("UserId: " + userDetails.getUserId());  // userId 출력
            }
            log.info("authentication.getName() : " + authentication.getName());  // email 출력
        }
    }
}