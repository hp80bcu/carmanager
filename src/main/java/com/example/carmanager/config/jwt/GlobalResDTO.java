package com.example.carmanager.config.jwt;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GlobalResDTO {
    private String msg;
    private int statusCode;

    public GlobalResDTO(String msg, int statusCode) {
        this.msg = msg;
        this.statusCode = statusCode;
    }
}
