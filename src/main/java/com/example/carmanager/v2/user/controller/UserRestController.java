package com.example.carmanager.v2.user.controller;

import com.example.carmanager.v2.jwt.dto.CustomOAuth2User;
import com.example.carmanager.v2.jwt.dto.UserDTO;
import com.example.carmanager.v2.user.dto.DeleteResponse;
import com.example.carmanager.v2.user.dto.UserResponse;
import com.example.carmanager.v2.user.entity.User;
import com.example.carmanager.v2.user.service.CustomOAuth2UserService;
import com.example.carmanager.v2.user.service.UserService;
import com.example.carmanager.v2.util.Response;
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
        log.info("사용자 전화번호 : " + userDetails.getPhoneNumber());
        log.info("사용자 집주소 : " + userDetails.getPhoneNumber());

        // 사용자 정보를 UserResponse 객체로 반환
        return new UserResponse(userDetails.getId(), userDetails.getName(), userDetails.getPhoneNumber(), userDetails.getAddress());
    }

    /* 사용자 정보 가져오기 */
    @GetMapping("/{userId}")
    public Response<UserDTO> getUserInfo(@PathVariable("userId") Long userId) {
        UserDTO userDTO = userService.getUserInfo(userId);// 서비스에서 사용자 정보 가져오기
        if (userDTO != null) {
            return Response.success(userDTO);
        } else {
            return Response.error(String.valueOf(HttpStatus.NOT_FOUND));
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

    /* 회원 정보 수정 */
    @PutMapping("/{userId}")
    public Response<UserDTO> modifyUser(@PathVariable("userId") Long userId, @RequestBody UserDTO userDTO) {
        String nickname = userDTO.getNickname();
        String phone = userDTO.getPhoneNumber();
        String address = userDTO.getAddress();
        userDTO = userService.modifyUser(userId, nickname, phone, address);// 서비스에서 사용자 정보 가져오기
        if (userDTO != null) {
            return Response.success(userDTO);
        } else {
            return Response.error(String.valueOf(HttpStatus.NOT_FOUND));
        }
    }

    @DeleteMapping("/{userId}")
    public Response<DeleteResponse> deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
        DeleteResponse deleteResponse = new DeleteResponse();
        deleteResponse.setMsg("탈퇴 성공");
        return Response.success(deleteResponse);
    }
}
