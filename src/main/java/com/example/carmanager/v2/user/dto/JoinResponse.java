package com.example.carmanager.v2.user.dto;

import com.example.carmanager.v2.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class JoinResponse {
    private Long userId;
    private String userName;

    public JoinResponse(User user) {
        this.userId = user.getUserId();
        this.userName = user.getNickname();
    }
}
