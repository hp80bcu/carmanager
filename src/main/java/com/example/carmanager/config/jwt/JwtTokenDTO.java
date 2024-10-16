package com.example.carmanager.config.jwt;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class JwtTokenDTO {
    private final String grantType;
    private final String authorizationType;
    private final String accessToken;
    private final String refreshToken;
    private final Long accessTokenExpiresIn;
}
