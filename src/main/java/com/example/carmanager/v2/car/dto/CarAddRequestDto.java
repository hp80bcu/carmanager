package com.example.carmanager.v2.car.dto;

import lombok.Data;

@Data
public class CarAddRequestDto {
    private String carNumber;
    private Long userId;
}
