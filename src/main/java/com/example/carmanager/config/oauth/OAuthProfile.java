package com.example.carmanager.config.oauth;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Builder
@Data
public class OAuthProfile {
    private final Map<String, Object> attributes;
    private final String name;
    private final String email;
    private String provider;

    public OAuthProfile(Map<String, Object> attributes, String name, String email, String provider) {
        this.attributes = attributes;
        this.name = name;
        this.email = email;
        this.provider = provider;
    }
}
