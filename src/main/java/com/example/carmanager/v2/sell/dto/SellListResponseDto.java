package com.example.carmanager.v2.sell.dto;

import lombok.Data;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.util.Date;

@Data
public class SellListResponseDto {
    private Long carId;
    private int howManyCar;
    private String profileImage;
    private String model;
    private Timestamp registDate;
    private Integer year;
    private int distance;
    private String region;
    private String fuel;
    private int price;


    // 모델 연도에서 연도만 추출하여 Integer로 저장
    public void setYear(Date year) {
        if (year != null) {
            // Date에서 연도만 추출하여 Integer로 저장
            this.year = year.toInstant().atZone(ZoneId.systemDefault()).getYear();
        } else {
            this.year = null;
        }
    }
}
