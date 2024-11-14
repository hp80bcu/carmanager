package com.example.carmanager.v2.sell.dto;

import lombok.Data;

@Data
public class SellAddRequestDto {
    Long userId;
    String carNumber;
    String[] outerOptions;
    String[] innerOptions;
    String[] safetyOptions;
    String[] multipleOptions;
    int distance;
    int price;
    String description;
    String[] innerPictures;
    String[] outerPictures;
}
