package com.example.carmanager.v2.sell.controller;

import com.example.carmanager.v2.sell.dto.*;
import com.example.carmanager.v2.util.Response;
import com.example.carmanager.v2.sell.service.SaleListService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sells")
@Slf4j
public class SellRestController {
    private final SaleListService saleListService;
    @PostMapping("")
    public Response<SellAddResponseDto> addCar(
            @RequestPart(name = "pictures", required = false) List<MultipartFile> pictures,
            @RequestPart(name = "sellAddRequestDto") String sellAddRequestDtoJson) throws IOException {

        // 1. JSON 문자열을 SellAddRequestDto 객체로 변환
        ObjectMapper objectMapper = new ObjectMapper();
        SellAddRequestDto sellAddRequestDto = objectMapper.readValue(sellAddRequestDtoJson, SellAddRequestDto.class);
        Long carId = sellAddRequestDto.getCarId();

        // 2. 서비스 호출: 차량 정보 추가
        SellAddResponseDto sellAddResponseDto = saleListService.addCar(sellAddRequestDto);

        try {
            // 3. 이미지 업로드 처리
            saleListService.uploadCarImages(carId, pictures);
            return Response.success(sellAddResponseDto);
        } catch (IOException e) {
            return Response.error("Image upload failed.");
        }
    }

    @GetMapping("")
    public Response<List<SellListResponseDto>> getSellList(@RequestParam(value = "company", required = false) String company,
                                                           @RequestParam(value = "model", required = false) String model,
                                                           @RequestParam(value = "detail", required = false) String detail,
                                                           @RequestParam(value = "carNum", required = false) String carNum,
                                                           @RequestParam(value = "sort", required = false) String sort
    ) {
        List<SellListResponseDto> myCarList = saleListService.getSellList(sort, company, model, detail);
        return Response.success(myCarList);
    }

    @GetMapping("/{carId}")
    public Response<SellListDetailResponseDto> getCarDetails(@PathVariable("carId") Long carId){
        SellListDetailResponseDto sellListResponseDto = saleListService.getCarDetails(carId);
        return Response.success(sellListResponseDto);
    }

    @GetMapping("/comparison-price/{carModel}")
    public Response<PriceComparisonResponseDto> getCarPriceComparison(@PathVariable("carModel") String carModel){
        PriceComparisonResponseDto priceComparisonResponseDto = saleListService.getCarComparionPrice(carModel);
        return Response.success(priceComparisonResponseDto);
    }
}
