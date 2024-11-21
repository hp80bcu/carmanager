package com.example.carmanager.v2.user.service;

import com.example.carmanager.v2.jwt.dto.UserDTO;
import com.example.carmanager.v2.user.entity.User;
import com.example.carmanager.v2.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public UserDTO getUserInfo(Long userId) {
        User user = userRepository.findByUserId(userId);
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getNickname());
        userDTO.setPhoneNumber(user.getPhone());
        userDTO.setAddress(user.getAddress());
        return userDTO;
    }

    // 사용자 정보 수정
    @Transactional
    public UserDTO modifyUser(Long userId, String nickname, String phone, String address){
        userRepository.modifyUser(userId, nickname, phone, address);
        User user = userRepository.findByUserId(userId);
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getNickname());
        userDTO.setPhoneNumber(user.getPhone());
        userDTO.setAddress(user.getAddress());
        return userDTO;
    }
    // 탈퇴
    public void deleteUser(Long userId){
        userRepository.deleteById(userId);
    }
}
