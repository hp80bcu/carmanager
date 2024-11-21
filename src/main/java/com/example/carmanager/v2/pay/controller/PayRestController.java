package com.example.carmanager.v2.pay.controller;

import com.example.carmanager.v2.pay.dto.PaymentRequest;
import com.example.carmanager.v2.pay.dto.PaymentResponse;
import com.example.carmanager.v2.util.Response;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
public class PayRestController {

    public Response<PaymentResponse> pay(@RequestBody PaymentRequest paymentRequest){
        return null;
    }
}
