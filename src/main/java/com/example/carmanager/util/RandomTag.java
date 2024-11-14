package com.example.carmanager.util;

import java.util.Random;

public class RandomTag {
    public static Long createHashtag() {
        Random random = new Random();
        int number = random.nextInt(10000); // 0에서 9999 사이의 숫자 생성
        String formattedNumber = String.format("%04d", number); // 4자리 문자열로 포맷팅
        return Long.valueOf(formattedNumber); // 문자열을 Long으로 변환하여 반환
    }
}
