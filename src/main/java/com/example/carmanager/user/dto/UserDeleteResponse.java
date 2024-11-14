package com.example.carmanager.user.dto;

import com.example.carmanager.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserDeleteResponse {
    private long userId;

    public UserDeleteResponse(User user) {
        this.userId = user.getUserId();
    }
}
