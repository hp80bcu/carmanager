package com.example.carmanager.user.dto;

import jakarta.persistence.Column;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@Data
public class JoinRequest {
    private String password;
    private String nickname;
    private String email;
    private Date birth;
    private String adress;
    private String phone;

    public void encryptPassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
    }
}
