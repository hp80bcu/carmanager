package com.example.carmanager.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RequiredArgsConstructor
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8080", "http://localhost:3000", "https://localhost:3000", "https://localhost:8080")
                .allowCredentials(true)
                .allowedMethods("OPTIONS", "GET", "POST", "PUT", "DELETE");
    }
}
