package com.example.carmanager.car.dto;

import lombok.Data;

@Data
public class CarAddRequestDto {
    private String carNumber;
    private Long userId;
}
