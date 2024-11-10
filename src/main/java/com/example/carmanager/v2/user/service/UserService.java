package com.example.carmanager.v2.user.service;

import com.example.carmanager.v2.user.entity.User;
import com.example.carmanager.v2.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public User getUserInfo(Long userId) {
        User user = userRepository.findByUserId(userId);
        return user;
    }
}
