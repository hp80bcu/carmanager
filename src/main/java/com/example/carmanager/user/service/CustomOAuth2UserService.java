package com.example.carmanager.user.service;

import com.example.carmanager.config.PasswordEncoder;
import com.example.carmanager.config.jwt.JwtTokenDTO;
import com.example.carmanager.config.oauth.OAuthAttributes;
import com.example.carmanager.config.oauth.OAuthProfile;
import com.example.carmanager.user.entity.CustomUser;
import com.example.carmanager.user.entity.User;
import com.example.carmanager.user.repository.UserRepository;
import com.example.carmanager.util.DomainExtract;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

import static com.nimbusds.oauth2.sdk.GrantType.PASSWORD;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final DefaultOAuth2UserService delegate;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        OAuthProfile oAuthProfile = OAuthAttributes.extract(registrationId, oAuth2User.getAttributes());
        User user = saveUser(oAuthProfile, registrationId);

        return new CustomUser(user, oAuthProfile);
    }

    private User saveUser(OAuthProfile oAuthProfile, String registrationId) {
        String password = passwordEncoder.bCryptPasswordEncoder(PASSWORD + UUID.randomUUID().toString().substring(0, 8)).toString();

        User check = userRepository.findByNicknameAndEmailAndProvider(oAuthProfile.getName(), oAuthProfile.getEmail(), registrationId);

        User user = null;
        if (check == null) {
            user = new User();
            user.setPassword(password);
            user.setNickname(oAuthProfile.getName());
            user.setEmail(oAuthProfile.getEmail());
            user.setProvider(registrationId);
            userRepository.save(user);
        }


        return user;
    }
}
