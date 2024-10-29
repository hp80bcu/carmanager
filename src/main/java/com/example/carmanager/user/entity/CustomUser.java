package com.example.carmanager.user.entity;

import com.example.carmanager.global.oauth2.profile.OAuthProfile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class CustomUser implements UserDetails, OAuth2User {

    private final User user;
    //추가
    private OAuthProfile oAuthProfile;

    public CustomUser(User user) {
        this.user = user;
    }

    //추가
    public CustomUser(User user, OAuthProfile oAuthProfile) {
        this.user = user;
        this.oAuthProfile = oAuthProfile;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    @Override
    public String getName() {
        return oAuthProfile.getName();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> "ROLE_USER");
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return oAuthProfile.getEmail();
    }

    public Long getUserId() {return user.getUserId();}

    public String getEmail() {
        return user.getEmail();
    }
}