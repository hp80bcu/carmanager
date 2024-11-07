package com.example.carmanager.car.controller;

import com.example.carmanager.car.dto.CarAddRequestDto;
import com.example.carmanager.car.dto.CarAddResponseDto;
import com.example.carmanager.car.dto.MyCarBasicResponseDto;
import com.example.carmanager.car.service.CarService;
import com.example.carmanager.global.oauth2.util.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cars/")

public class CarRestController {

    private final CarService carService;

    /**
     * 차량 추가
     * @param carAddRequestDto
     * @return
     */
    @PostMapping("/add-car")
    public Response<CarAddResponseDto> myCarAdd(@RequestBody CarAddRequestDto carAddRequestDto){
        CarAddResponseDto carAddResponseDto= carService.myCarAdd(carAddRequestDto);
        return Response.success(carAddResponseDto);
    }

    @GetMapping("/{userId}")
    public Response<List<MyCarBasicResponseDto>> getMycar(@PathVariable("userId") Long userId){
        List<MyCarBasicResponseDto> myCarList = carService.getMyCar();
        return Response.success(myCarList);
    }
}
