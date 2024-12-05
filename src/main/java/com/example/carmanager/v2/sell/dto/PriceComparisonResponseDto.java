package com.example.carmanager.v2.sell.dto;

import lombok.Data;

@Data
public class PriceComparisonResponseDto {
    int highPrice;
    int avgPrice;
    int lowPrice;
}
