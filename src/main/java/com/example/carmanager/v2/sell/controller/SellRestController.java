package com.example.carmanager.v2.sell.controller;

import com.example.carmanager.v2.sell.dto.SellListDetailResponseDto;
import com.example.carmanager.v2.sell.dto.SellListResponseDto;
import com.example.carmanager.v2.util.Response;
import com.example.carmanager.v2.sell.dto.SellAddRequestDto;
import com.example.carmanager.v2.sell.dto.SellAddResponseDto;
import com.example.carmanager.v2.sell.service.SaleListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sells/*")
@Slf4j
public class SellRestController {
    private final SaleListService saleListService;
    @PostMapping("/add-selllist")
    public Response<SellAddResponseDto> addCar(@RequestPart(name = "pictures", required = false) List<MultipartFile> pictures,
                                               @RequestPart(name = "sellAddRequestDto") SellAddRequestDto sellAddRequestDto) throws IOException {
        Long carId = sellAddRequestDto.getCarId();

        SellAddResponseDto sellAddResponseDto = saleListService.addCar(sellAddRequestDto);

        try {
            saleListService.uploadCarImages(carId, pictures);
            return Response.success(sellAddResponseDto);
        } catch (IOException e) {
            return Response.error("Image upload failed.");
        }
    }

    @GetMapping("/")
    public Response<List<SellListResponseDto>> getSellList(){
        List<SellListResponseDto> myCarList = saleListService.getAllCars();
        return Response.success(myCarList);
    }

    @GetMapping("/{carId}")
    public Response<SellListDetailResponseDto> getCarDetails(@PathVariable("carId") Long carId){
        SellListDetailResponseDto sellListResponseDto = saleListService.getCarDetails(carId);
        return Response.success(sellListResponseDto);
    }
}
