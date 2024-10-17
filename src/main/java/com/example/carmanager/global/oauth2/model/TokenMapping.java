package com.example.carmanager.global.oauth2.model;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TokenMapping {
    private final String accessToken;
    private final String refreshToken;

    @Builder
    public TokenMapping(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}