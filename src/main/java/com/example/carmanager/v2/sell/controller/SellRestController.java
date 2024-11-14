package com.example.carmanager.v2.sell.controller;

import com.example.carmanager.v2.util.Response;
import com.example.carmanager.v2.s3.service.S3UploadService;
import com.example.carmanager.v2.sell.dto.SellAddRequestDto;
import com.example.carmanager.v2.sell.dto.SellAddResponseDto;
import com.example.carmanager.v2.sell.service.SaleListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cars/*")
@Slf4j
public class SellRestController {
    private final SaleListService saleListService;
    @PostMapping("/add-images")
    public Response<SellAddResponseDto> addCar(@RequestPart(required = false, name = "pictures") MultipartFile[] pictures,
                                               @RequestPart(name = "request") @Validated final SellAddRequestDto sellAddRequestDto) throws IOException {
        Long carId = sellAddRequestDto.getCarId();

        SellAddResponseDto sellAddResponseDto = saleListService.addCar(sellAddRequestDto);

        try {
            saleListService.uploadCarImages(carId, pictures);
            return Response.success(sellAddResponseDto);
        } catch (IOException e) {
            return Response.error("Image upload failed.");
        }
    }
}