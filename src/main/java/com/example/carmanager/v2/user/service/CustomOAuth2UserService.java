package com.example.carmanager.v2.user.service;

import com.example.carmanager.v2.jwt.dto.*;
import com.example.carmanager.v2.user.entity.User;
import com.example.carmanager.v2.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    public CustomOAuth2UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);
        log.info("oauth2 : " + oAuth2User);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response = null;
        if (registrationId.equals("naver")) {

            oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
        } else if (registrationId.equals("google")) {

            oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
        } else if (registrationId.equals("kakao")) {

            oAuth2Response = new KakaoResponse(oAuth2User.getAttributes());
        } else {

            return null;
        }

        log.info("Email : " + oAuth2Response.getEmail());
        log.info("서비스 : " + oAuth2Response.getProvider());
        User existData = userRepository.findUserByEmailAndProvider(oAuth2Response.getEmail(), oAuth2Response.getProvider());

        if (existData == null) {

            User User = new User();
            User.setNickname(oAuth2Response.getName());
            User.setEmail(oAuth2Response.getEmail());
            User.setProvider(oAuth2Response.getProvider());
            User.setRole("ROLE_USER");

            userRepository.save(User);

            UserDTO userDTO = new UserDTO();
            userDTO.setNickname(oAuth2Response.getName());
            userDTO.setRole("ROLE_USER");

            return new CustomOAuth2User(userDTO);
        } else {
            UserDTO userDTO = new UserDTO();
            userDTO.setUserId(existData.getUserId());
            userDTO.setNickname(existData.getNickname());
            userDTO.setRole("ROLE_USER");
            log.info("CustomOAuth2UserService단에서의 id :" + userDTO.getUserId());

            return new CustomOAuth2User(userDTO);
        }
    }
}