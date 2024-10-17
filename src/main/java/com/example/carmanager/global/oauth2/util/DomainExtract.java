package com.example.carmanager.global.oauth2.util;

public class DomainExtract {
    public static String extractDomain(String email) {
        int atIndex = email.indexOf("@");
        int dotIndex = email.lastIndexOf(".com"); // ".com"의 인덱스 찾기
        if (atIndex != -1 && dotIndex != -1) {
            return email.substring(atIndex + 1, dotIndex); // "@" 다음부터 ".com" 앞까지
        } else {
            return null; // 이메일 형식이 아닌 경우
        }
    }

    public static String extractUsername(String email) {
        // 이메일에서 '@' 문자의 위치를 찾음
        int atIndex = email.indexOf('@');

        // '@' 문자 앞의 부분을 잘라서 반환
        if (atIndex != -1) {
            return email.substring(0, atIndex);
        } else {
            // '@' 문자가 없으면 원래 문자열 반환
            return email;
        }
    }
}
