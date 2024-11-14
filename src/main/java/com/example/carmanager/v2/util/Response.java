package com.example.carmanager.v2.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Response<T> {
    private String resultCode;
    private T result;
    public static <T> Response<T> error(String resultCode){
        return new Response<>(resultCode, null);
    }
    public static <T> Response<T> success(T result){
        return new Response<>("SUCCESS", result);
    }
}