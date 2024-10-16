package com.example.carmanager.user.dto;

import lombok.Data;

@Data
public class CreateAccessTokenRequest {
    private String refreshToken;
}
