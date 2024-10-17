package com.example.carmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.carmanager")
public class CarmanagerApplication {
	public static void main(String[] args) {
		SpringApplication.run(CarmanagerApplication.class, args);
	}
}
