package com.example.carmanager.v2.jwt.dto;

import com.example.carmanager.v2.jwt.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Slf4j
public class CustomOAuth2User implements OAuth2User {

    private final UserDTO userDTO;

    public CustomOAuth2User(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    @Override
    public Map<String, Object> getAttributes() {

        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add((GrantedAuthority) userDTO::getRole);

        return collection;
    }

    @Override
    public String getName() {
        return userDTO.getUsername();
    }

    public Long getId() {
        log.info("CustomOAuth2User단에서의 id :" + userDTO.getUserId());
        return userDTO.getUserId();
    }

}