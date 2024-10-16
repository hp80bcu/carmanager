package com.example.carmanager.config.oauth;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OAuthProfile {
    private final String name;
    private final String email;
    private String provider;

    public OAuthProfile(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public OAuthProfile(String name, String email, String provider) {
        this.name = name;
        this.email = email;
        this.provider = provider;
    }
}
