package com.example.carmanager.user.dto;

import com.example.carmanager.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserJoinResponse {
    private long userId;
    private String userName;

    public UserJoinResponse(User user) {
        this.userId = user.getUserId();
        this.userName = user.getNickname();
    }
}
