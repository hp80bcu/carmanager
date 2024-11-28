package com.example.carmanager.v2.sell.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class SellAddRequestDto {
    Long carId;
    String carNumber;
    String[] Options;
    int distance;
    int price;
    String description;
    MultipartFile[] Pictures;
}
