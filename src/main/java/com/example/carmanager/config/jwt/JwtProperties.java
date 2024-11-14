package com.example.carmanager.config.jwt;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("jwt")
@RequiredArgsConstructor
public class JwtProperties {
    private String issuer;
    private String secretKey;
}
