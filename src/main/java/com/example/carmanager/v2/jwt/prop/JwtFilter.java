package com.example.carmanager.v2.jwt.prop;

import com.example.carmanager.v2.jwt.dto.CustomOAuth2User;
import com.example.carmanager.v2.jwt.dto.UserDTO;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorization = null;
        Cookie[] cookies = request.getCookies();

        // cookies가 null일 수 있으므로 null 체크
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                log.info(cookie.getName());
                if (cookie.getName().equals("Authorization")) {
                    authorization = cookie.getValue();
                    break;  // 'token'을 찾으면 반복문 종료
                }
            }
        }

        // Authorization 헤더나 쿠키에서 토큰을 찾을 수 없으면 필터 체인 진행
        if (authorization == null) {
            log.info("token null");
            filterChain.doFilter(request, response);
            return;
        }

        // 토큰 소멸 시간 검증
        if (jwtUtil.isExpired(authorization)) {
            log.info("token expired");
            filterChain.doFilter(request, response);
            return;
        }

        // 토큰에서 username과 role 획득
        String username = jwtUtil.getUsername(authorization);
        String role = jwtUtil.getRole(authorization);
        Long id = jwtUtil.getUserId(authorization);

        // UserDTO를 생성하여 값 set
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(username);
        userDTO.setRole(role);
        userDTO.setUserId(id);

        // UserDetails에 회원 정보 객체 담기
        CustomOAuth2User customOAuth2User = new CustomOAuth2User(userDTO);

        // 스프링 시큐리티 인증 토큰 생성
        Authentication authToken = new UsernamePasswordAuthenticationToken(customOAuth2User, null, customOAuth2User.getAuthorities());

        // 세션에 사용자 등록
        SecurityContextHolder.getContext().setAuthentication(authToken);

        // 필터 체인 진행
        filterChain.doFilter(request, response);
    }
}