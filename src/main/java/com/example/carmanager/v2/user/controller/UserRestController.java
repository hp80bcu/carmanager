package com.example.carmanager.v2.user.controller;

import com.example.carmanager.v2.jwt.dto.CustomOAuth2User;
import com.example.carmanager.v2.jwt.dto.UserDTO;
import com.example.carmanager.v2.user.dto.UserResponse;
import com.example.carmanager.v2.user.entity.User;
import com.example.carmanager.v2.user.service.CustomOAuth2UserService;
import com.example.carmanager.v2.user.service.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/users/")
public class UserRestController {
    private final UserService userService;
    /* 로그인 된 사용자 정보 가져오기 */
    @GetMapping("/userinfo")
    public UserResponse getUserInfo(@AuthenticationPrincipal CustomOAuth2User userDetails) {
        // userDetails에서 UserDTO 가져오기
        log.info("사용자 id : " + userDetails.getId());
        // 사용자 정보를 UserResponse 객체로 반환
        return new UserResponse(userDetails.getId(), userDetails.getName());
    }

    /* 사용자 정보 가져오기 */
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserInfo(@PathVariable("userId") Long userId) {
        User user = userService.getUserInfo(userId);  // 서비스에서 사용자 정보 가져오기
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /* 로그아웃 */
    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        // "Authorization" 쿠키 삭제
        Cookie authCookie = new Cookie("Authorization", null);
        authCookie.setMaxAge(0); // 쿠키 만료 시간 0으로 설정 (삭제)
        authCookie.setPath("/");  // 쿠키 경로를 명시적으로 설정
        response.addCookie(authCookie);

        // "token" 쿠키 삭제
        Cookie tokenCookie = new Cookie("token", null);
        tokenCookie.setMaxAge(0); // 쿠키 만료 시간 0으로 설정 (삭제)
        tokenCookie.setPath("/");  // 쿠키 경로를 명시적으로 설정
        response.addCookie(tokenCookie);

        // 로그아웃 성공 메시지
        return ResponseEntity.ok("Logged out successfully");
    }

//    @GetMapping("/userinfo")
//    public String getUserInfo(@RequestHeader HttpHeaders header) {
//        // 사용자 정보를 UserResponse 객체로 반환
//        log.info("헤더정보 : " + header);
//        return null;
//    }
}
