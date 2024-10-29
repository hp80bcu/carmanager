package com.example.carmanager.global.oauth2;

import com.example.carmanager.global.oauth2.model.TokenMapping;
import com.example.carmanager.global.oauth2.profile.OAuthAttributes;
import com.example.carmanager.global.oauth2.profile.OAuthProfile;
import com.example.carmanager.global.oauth2.util.TokenProvider;
import com.example.carmanager.user.dto.JoinResponse;
import com.example.carmanager.user.entity.CustomUser;
import com.example.carmanager.user.entity.RefreshToken;
import com.example.carmanager.user.entity.User;
import com.example.carmanager.user.repository.RefreshTokenRepository;
import com.example.carmanager.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.nimbusds.oauth2.sdk.GrantType.PASSWORD;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        OAuthProfile oAuthProfile = OAuthAttributes.extract(registrationId, oAuth2User.getAttributes());

        User user = saveUser(oAuthProfile, registrationId);

        if(user == null){
            User loginUser = userRepository.findByNicknameAndEmailAndProvider(oAuthProfile.getName(), oAuthProfile.getEmail(), registrationId);
            loginUser.setPassword(null);
            return new CustomUser(loginUser, oAuthProfile);
        }

        return new CustomUser(user, oAuthProfile);
    }

    private User saveUser(OAuthProfile oAuthProfile, String registrationId) {
        String password = passwordEncoder.encode(PASSWORD + UUID.randomUUID().toString().substring(0, 8)).toString();

        User check = userRepository.findByNicknameAndEmailAndProvider(oAuthProfile.getName(), oAuthProfile.getEmail(), registrationId);

        User user = null;
        RefreshToken refreshToken = null;
        if (check == null) {
            user = new User();
            user.setPassword(password);
            user.setNickname(oAuthProfile.getName());
            user.setEmail(oAuthProfile.getEmail());
            user.setProvider(registrationId);

            userRepository.save(user);
            Long userId = userRepository.findUserIdByEmailAndProvider(oAuthProfile.getEmail(), registrationId);
            refreshToken.setUserId(userId);
            String token = String.valueOf(tokenProvider.createToken(oAuthProfile.getEmail()));
            refreshToken.setRefreshToken(token);

            refreshTokenRepository.save(refreshToken);
        }


        return user;
    }
}
