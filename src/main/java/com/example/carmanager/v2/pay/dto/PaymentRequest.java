package com.example.carmanager.v2.pay.dto;

import lombok.Data;

@Data
public class PaymentRequest {
    String paymentCode;
    String price;
    Long carId;
    Long sellerId;
    Long buyerId;
}
