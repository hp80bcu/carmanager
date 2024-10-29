package com.example.carmanager.user.service;

import com.example.carmanager.user.dto.JoinRequest;
import com.example.carmanager.user.entity.CustomUser;
import com.example.carmanager.user.entity.User;
import com.example.carmanager.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(username).get();
        return new CustomUser(user);
    }

    public User saveUSer(JoinRequest request) {
        request.encryptPassword(passwordEncoder);
        User user = User.of(request);
        userRepository.save(user);
        return user;
    }
}

