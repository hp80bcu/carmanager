package com.example.carmanager.user.controller;

import com.example.carmanager.user.dto.UserJoinRequest;
import com.example.carmanager.user.dto.UserJoinResponse;
import com.example.carmanager.user.entity.User;
import com.example.carmanager.user.service.UserService;
import com.example.carmanager.util.Response;
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
    public Response<UserJoinResponse> join(@RequestBody UserJoinRequest userJoinRequest) {
        User join = userService.join(userJoinRequest);

        UserJoinResponse userJoinResponse = new UserJoinResponse(join);
        return Response.success(userJoinResponse);
    }
}
