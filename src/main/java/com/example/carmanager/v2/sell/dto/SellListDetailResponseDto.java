package com.example.carmanager.v2.sell.dto;

import com.example.carmanager.v2.car.entity.CarImage;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class SellListDetailResponseDto {
    /* 헤더 */
    Long carId;
    String carNum;
    String carModel;
    int price;

    /* 판매자 정보 */
    String name;
    String phoneNumber;
    String email;

    /* 차량 기본 정보 */
    Date year;
    int distance;
    String fuel;
    String shift;
    Double efficeiency;
    String type;
    int displacement;
    String color;

    /* 사진 정보 */
    List<CarImage> images;
    /* 옵션 정보 */
    String options;
    /* 차량 설명 */
    String carDescription;
}
