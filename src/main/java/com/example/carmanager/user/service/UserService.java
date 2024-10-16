package com.example.carmanager.user.service;

import com.example.carmanager.config.jwt.GlobalResDTO;
import com.example.carmanager.config.jwt.JwtTokenDTO;
import com.example.carmanager.config.jwt.TokenProvider;
import com.example.carmanager.config.oauth.OAuthAttributes;
import com.example.carmanager.config.oauth.OAuthProfile;
import com.example.carmanager.user.dto.UserJoinRequest;
import com.example.carmanager.user.entity.RefreshToken;
import com.example.carmanager.user.entity.User;
import com.example.carmanager.user.repository.RefreshTokenRepository;
import com.example.carmanager.user.repository.UserRepository;
import com.example.carmanager.util.RandomTag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final BCryptPasswordEncoder encoder;
    private final TokenProvider tokenProvider;

    @Value("${jwt.secret_key}")
    private String secretKey;
    private long expiredTimeMs = 1000 * 60 * 60; //1시간

    public void SaveUser(User userProfile) {
        userRepository.save(userProfile);
    }

    @Transactional
    public User join(OAuth2UserRequest userRequest) {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate =
                new DefaultOAuth2UserService();

        // 소셜에서 인증받아서 가져온 유저 정보를 담고 있다.
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        // 어떤 서비스인지(구글, 네이버 등등) -> 로그 찍어보면 naver 혹은 google 이 나온다.
        String registrationId = userRequest.getClientRegistration()
                .getRegistrationId();


        // OAuth2 로그인 진행 시 키가 되는 필드 값, 구글은 sub 네이버는 response 라는 이름을 갖는다.
        String userNameAttributeName = userRequest.getClientRegistration()
                .getProviderDetails()
                .getUserInfoEndpoint()
                .getUserNameAttributeName();

        Map<String, Object> attributes = oAuth2User.getAttributes();
        //서비스 마다 다른 키와 벨류 값을 변환하여 객체를 만든다.
        OAuthProfile oAuthProfile = OAuthAttributes.extract(registrationId, attributes);

        log.info("🌈 이름 [{}]", oAuthProfile.getName());
        log.info("🌈 이메일 [{}]", oAuthProfile.getEmail());

        // 기존에 있는 유저인지 체크
        User check = userRepository.findByNicknameAndEmailAndProvider(oAuthProfile.getName(), oAuthProfile.getEmail(), registrationId);
        User user = null;
        if (check == null) {
            user = new User();
            user.setNickname(oAuthProfile.getName());
            user.setEmail(oAuthProfile.getEmail());
            user.setProvider(registrationId);
            SaveUser(user);
        }

        return user;
    }

    @Transactional
    public JwtTokenDTO login(OAuth2User oauth2User) {
        // 1. OAuth2 인증된 사용자의 인증 정보를 기반으로 Authentication 객체 생성
        Authentication authentication = new OAuth2AuthenticationToken(
                oauth2User,
                oauth2User.getAuthorities(),
                oauth2User.getName()
        );

        // 2. 인증 정보를 기반으로 JWT 토큰 생성
        JwtTokenDTO jwtTokenDTO = tokenProvider.generateTokenDto(authentication);

        // 3. RefreshToken 저장
        RefreshToken refreshToken = RefreshToken.builder()
                .userId(oauth2User.getName())  // OAuth2 사용자 이름을 key로 저장
                .value(jwtTokenDTO.getRefreshToken())  // 생성한 RefreshToken 저장
                .build();

        refreshTokenRepository.save(refreshToken);

        // 4. JWT 토큰 반환
        return jwtTokenDTO;
    }

    @Transactional
    public JwtTokenDTO reissue(TokenRequestDto tokenRequestDto) {
        // 1. Refresh Token 검증
        if (!tokenProvider.validateToken(tokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("Refresh Token 이 유효하지 않습니다.");
        }

        // 2. Access Token 에서 Member ID 가져오기
        Authentication authentication = tokenProvider.getAuthentication(tokenRequestDto.getAccessToken());

        // 3. 저장소에서 Member ID 를 기반으로 Refresh Token 값 가져옴
        RefreshToken refreshToken = refreshTokenRepository.findByKey(authentication.getName())
                .orElseThrow(() -> new RuntimeException("로그아웃 된 사용자입니다."));

        // 4. Refresh Token 일치하는지 검사
        if (!refreshToken.getValue().equals(tokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("토큰의 유저 정보가 일치하지 않습니다.");
        }

        // 5. 새로운 토큰 생성
        JwtTokenDTO JwtTokenDTO = tokenProvider.generateJwtTokenDTO(authentication);

        // 6. 저장소 정보 업데이트
        RefreshToken newRefreshToken = refreshToken.updateValue(JwtTokenDTO.getRefreshToken());
        refreshTokenRepository.save(newRefreshToken);

        // 토큰 발급
        return JwtTokenDTO;
    }
}

