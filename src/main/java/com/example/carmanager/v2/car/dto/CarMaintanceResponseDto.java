package com.example.carmanager.v2.car.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CarMaintanceResponseDto {
    String carNum;
    String maintanceCompany;
    Date maintanceDate;
    Double performanceCheckDistance;
    String content;
}
