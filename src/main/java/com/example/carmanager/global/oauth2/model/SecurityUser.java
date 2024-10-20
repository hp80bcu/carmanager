package com.example.carmanager.global.oauth2.model;

import com.example.carmanager.global.oauth2.profile.OAuthProfile;
import com.example.carmanager.user.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public class SecurityUser implements UserDetails, OAuth2User {

    private final User user;
    private OAuthProfile oAuthProfile;

    public SecurityUser(User user, OAuthProfile oAuthProfile) {
        this.user = user;
        this.oAuthProfile = oAuthProfile;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> "ROLE_USER");
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getNickname();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getEmail() {
        return user.getEmail();
    }

    @Override
    public String getName() {
        return oAuthProfile.getName();
    }
}
