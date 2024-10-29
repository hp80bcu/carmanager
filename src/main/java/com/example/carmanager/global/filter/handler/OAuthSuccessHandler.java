package com.example.carmanager.global.filter.handler;

import com.example.carmanager.global.OAuthAuthorizationRequestBasedOnCookieRepository;
import com.example.carmanager.global.oauth2.model.TokenMapping;
import com.example.carmanager.global.oauth2.util.CookieUtil;
import com.example.carmanager.global.oauth2.util.TokenProvider;
import com.example.carmanager.user.entity.CustomUser;
import com.example.carmanager.user.repository.RefreshTokenRepository;
import com.example.carmanager.user.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.time.Duration;
import java.util.Optional;

import static com.example.carmanager.global.OAuthAuthorizationRequestBasedOnCookieRepository.REDIRECT_URI_PARAM_COOKIE_NAME;

@Component
@Slf4j
@RequiredArgsConstructor
public class OAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private static final String TOKEN = "token";
    private static final String REFRESH_TOKEN = "refreshToken";

    private final TokenProvider tokenProvider;
    private final OAuthAuthorizationRequestBasedOnCookieRepository cookieAuthorizationRequestRepository;

    @Override
    @Transactional
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String targetUrl = determineTargetUrl(request, response, authentication);
        TokenMapping tokenMapping = saveUser(authentication);
        getRedirectStrategy().sendRedirect(request, response, getRedirectUrl(targetUrl, tokenMapping));
    }

    @Override
    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        Optional<String> redirectUri = CookieUtil.getCookie(request, REDIRECT_URI_PARAM_COOKIE_NAME).map(Cookie::getValue);
        clearAuthenticationAttributes(request, response);
        return redirectUri.orElse("/");
    }

    private TokenMapping saveUser(Authentication authentication) {
        CustomUser securityUser = (CustomUser) authentication.getPrincipal();
        String email = securityUser.getEmail();

        TokenMapping token = tokenProvider.createToken(email);


        return token;
    }

    private String getRedirectUrl(String targetUrl, TokenMapping token) {
        return UriComponentsBuilder.fromUriString(targetUrl)
                .queryParam(TOKEN, token.getAccessToken())
                .queryParam(REFRESH_TOKEN, token.getRefreshToken())
                .build().toUriString();
    }

    protected void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {
        cookieAuthorizationRequestRepository.removeAuthorizationRequestCookies(request, response);
    }
}
