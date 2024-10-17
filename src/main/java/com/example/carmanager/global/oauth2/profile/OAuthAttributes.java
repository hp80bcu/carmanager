package com.example.carmanager.global.oauth2.profile;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;

public enum OAuthAttributes {
    GOOGLE("google", attributes -> new OAuthProfile(
            attributes,
            attributes.get("sub").toString(),
            attributes.get("name").toString(),
            attributes.get("email").toString()
    )),


    NAVER("naver", attributes -> {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");
        return new OAuthProfile(
                response,
                response.get("id").toString(),
                response.get("name").toString(),
                response.get("email").toString()
        );
    }),

    KAKAO("kakao", (attributes) -> {

        Map<String, Object> response = (Map<String, Object>) attributes.get("properties");
        Map<String, String> account = (Map<String, String>) attributes.get("kakao_account");

        return new OAuthProfile(
                response,
                response.get("nickname").toString(),
                response.get("name").toString(),
                account.get("email")
        );
    });

    private final String registrationId; // 로그인한 서비스(ex) google, naver..)
    private final Function<Map<String, Object>, OAuthProfile> of; // 로그인한 사용자의 정보를 통하여 UserProfile을 가져옴

    OAuthAttributes(String registrationId, Function<Map<String, Object>, OAuthProfile> of) {
        this.registrationId = registrationId;
        this.of = of;
    }

    public static OAuthProfile extract(String registrationId, Map<String, Object> attributes) {
        return Arrays.stream(values())
                .filter(value -> registrationId.equals(value.registrationId))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new)
                .of.apply(attributes);
    }
}

