package com.example.carmanager.v2.car.controller;

import com.example.carmanager.v2.car.dto.*;
import com.example.carmanager.v2.car.service.CarService;
import com.example.carmanager.v2.jwt.prop.Response;
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

    @GetMapping("/{carId}/maintance")
    public Response<List<CarMaintanceResponseDto>> getMyCarMaintance(@PathVariable("carId") Long carId){
        List<CarMaintanceResponseDto> myCarList = carService.getMyCarMaintance(carId);
        return Response.success(myCarList);
    }
}
