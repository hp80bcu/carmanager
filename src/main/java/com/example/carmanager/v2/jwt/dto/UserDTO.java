package com.example.carmanager.v2.jwt.dto;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
public class UserDTO {
    private Long userId;
    private String username;
    private String role;
}