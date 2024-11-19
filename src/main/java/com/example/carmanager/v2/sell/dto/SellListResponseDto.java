package com.example.carmanager.v2.sell.dto;

import lombok.Data;

import java.util.Date;

@Data
public class SellListResponseDto {
    private Long carId;
    private int howManyCar;
    private String profileImage;
    private String model;
    private Date registDate;
    private Date year;
    private int distance;
    private String region;
    private String fuel;
    private int price;
}
