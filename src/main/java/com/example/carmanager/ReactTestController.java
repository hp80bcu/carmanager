package com.example.carmanager;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class ReactTestController {

    @GetMapping("/api/test")
    public String getCurrentTime() {
        return "테스트";
    }
}
