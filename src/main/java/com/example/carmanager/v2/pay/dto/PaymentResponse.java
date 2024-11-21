package com.example.carmanager.v2.pay.dto;

import lombok.Data;

@Data
public class PaymentResponse {
    String code;
    String seller;
    String buyer;
}
