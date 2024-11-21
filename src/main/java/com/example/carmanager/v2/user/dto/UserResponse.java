package com.example.carmanager.v2.user.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserResponse {

    private final Long userId;
    private final String username;
    private final String phoneNumber;
    private final String address;
}
