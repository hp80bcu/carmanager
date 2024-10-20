package com.example.carmanager.global.oauth2.profile;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Builder
@Data
public class OAuthProfile {
    private final String name;
    private final String email;
    public OAuthProfile(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
