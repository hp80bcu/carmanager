package com.example.carmanager.user.dto;

import com.example.carmanager.user.entity.User;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserJoinRequest {
    private String nickname;
    private String email;
    private Date birth;
    private String adress;
    private String phone;
    private String provider;

    public User toEntity(){
        return new User(nickname, email, birth, adress, phone, provider);
    }
}
