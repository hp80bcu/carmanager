package com.example.carmanager.v2.pay.service;

import com.example.carmanager.v2.pay.dto.PaymentRequest;
import com.example.carmanager.v2.pay.dto.PaymentResponse;
import com.example.carmanager.v2.util.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class PayService {
    //@Value("${imp.api.key}")
    //private String key;

    //@Value("${imp.api.secretkey}")
    //private String secret;

    public Response<PaymentResponse> pay(PaymentRequest paymentRequest){
        return null;
    }
}
