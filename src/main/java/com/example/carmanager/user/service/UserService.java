package com.example.carmanager.user.service;

import com.example.carmanager.config.jwt.GlobalResDTO;
import com.example.carmanager.config.jwt.TokenProvider;
import com.example.carmanager.user.dto.UserJoinRequest;
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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
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

    /**
     * 회원가입
     * @param userJoinRequest
     * @return
     */
    @Transactional
    public User join(UserJoinRequest userJoinRequest){
        User user = userJoinRequest.toEntity();
        user.setUserId(RandomTag.createHashtag());

        // User 저장 (Cascade로 PreferGenre도 자동 저장)
        userRepository.save(user);
        return user;
    }

    @Transactional(readOnly = true)
    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + userId));
    }
}

