package com.example.carmanager.config.oauth;

import com.example.carmanager.config.jwt.JwtTokenDTO;
import com.example.carmanager.config.jwt.TokenProvider;
import com.example.carmanager.user.dto.OAuthLoginRequest;
import com.example.carmanager.user.entity.RefreshToken;
import com.example.carmanager.user.entity.User;
import com.example.carmanager.user.repository.RefreshTokenRepository;
import com.example.carmanager.user.repository.UserRepository;
import com.example.carmanager.util.CookieUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor
public class OAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Value("${jwt.secret_key}")
    private String key;

    @Value("${app.oauth2.authorizedRedirectUri}")
    private String redirectUri;
    private String targetUrl;

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final OAuthAuthorizationRequestBasedOnCookieRepository authorizationRequestRepository;
    private final TokenProvider tokenProvider;

    public static final String REFRESH_TOKEN_COOKIE_NAME = "refresh_token";
    public static final Duration REFRESH_TOKEN_DURATION = Duration.ofDays(14);
    public static final Duration ACCESS_TOKEN_DURATION = Duration.ofDays(1);
//    public static final String REDIRECT_PATH = "";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {

        OAuth2User oAuth2User = (OAuth2User)authentication.getPrincipal();
        Map<String, Object> attributes = oAuth2User.getAttributes();
        String realName = null;
        String email = null;
        String provider = null;

        // 구글 인증시 아래 로직
        if(attributes.get("sub") != null) {
            realName = oAuth2User.getAttribute("name");
            email = oAuth2User.getAttribute("email");
            provider = "google";
            log.info("🌈 구글 인증 시 이름 추출 [{}] || 이메일 추출 [{}]", realName, email);
        }

        // 네이버 인증 시 아래 로직
        if(attributes.get("response") != null){
            Map<String, Object> response2 =(Map<String, Object>) attributes.get("response");
            realName = (String) response2.get("name");
            email = (String) response2.get("email");
            provider = "naver";
            log.info("🌈 네이버 인증 시 이름 추출 [{}] || 이메일 추출 [{}] || 프로바이더 추출[{}]",realName,email,provider);
        }

        // 카카오 인증 시 아래 로직
        if(attributes.get("id") != null){
            Map<String, Object> kakaoAccount =(Map<String, Object>) attributes.get("kakao_account");
            Map<String, String> profile = (Map)kakaoAccount.get("profile");

            realName = profile.get("nickname");
            email = (String) kakaoAccount.get("email");
            provider = "kakao";
            log.info("🌈 카카오 인증 시 이름 추출 [{}] || 이메일 추출 [{}]",realName,email);
        }

        // 이름과 이메일이 둘다 일치하는 회원이 저장되어있을 것
        User foundUser = userRepository.findByNicknameAndEmailAndProvider(realName, email, provider);
        String foundAccount = foundUser.getEmail();
        log.info("🌈 소셜 로그인 인증한 계정명 [{}]",foundAccount);

        OAuthLoginRequest oAuthLoginRequest = new OAuthLoginRequest();
        oAuthLoginRequest.setUsername(realName);
        oAuthLoginRequest.setId(foundUser.getUserId());

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


        //clearAuthenticationAttributes(request, response);

        Authentication newAuthentication = new UsernamePasswordAuthenticationToken(
                foundUser,
                null,
                foundUser.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);  // 인증 객체 설정

        response.sendRedirect("");
    }

    @Transactional
    public JwtTokenDTO login(OAuth2User oauth2User) {

        // 4. JWT 토큰 반환
        return jwtTokenDTO;
    }
}
