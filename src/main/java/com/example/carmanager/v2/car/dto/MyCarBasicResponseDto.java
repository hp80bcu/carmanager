package com.example.carmanager.v2.car.dto;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
public class MyCarBasicResponseDto {
    private Long carId;
    private String carNum;
    private String image;
    private String company;
    private String model;
    private String modelDetail;
    private Date date;
    private int distance;
    private String color;
    private Timestamp firstRegisterDate;
    private Double displacement;
    private String fuel;
    private String shift;
}
