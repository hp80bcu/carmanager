package com.example.carmanager.user.controller;

import com.example.carmanager.user.dto.JoinRequest;
import com.example.carmanager.user.dto.JoinResponse;
import com.example.carmanager.user.entity.User;
import com.example.carmanager.user.service.UserService;
import com.example.carmanager.global.oauth2.util.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/users/")
public class UserRestController {
    private final UserService userService;
    /* 회원가입 */
    @PostMapping("/join")
    public Response<JoinResponse> join(@RequestBody JoinRequest JoinRequest) {
        User join = userService.saveUSer(JoinRequest);

        JoinResponse userJoinResponse = new JoinResponse(join);
        return Response.success(userJoinResponse);
    }
}