package com.example.carmanager.car.service;

import com.example.carmanager.car.dto.CarAddRequestDto;
import com.example.carmanager.car.dto.CarAddResponseDto;
import com.example.carmanager.car.dto.MyCarBasicResponseDto;
import com.example.carmanager.car.entity.CarBasic;
import com.example.carmanager.car.repository.CarBasicRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CarService {
    private final CarBasicRepository carBasicRepository;

    /**
     * 차량 추가
     * @param carAddRequestDto
     * @return
     */
    public CarAddResponseDto myCarAdd(CarAddRequestDto carAddRequestDto){
        String carNumber = carAddRequestDto.getCarNumber();
        Long userId = carAddRequestDto.getUserId();
        CarBasic carBasic = carBasicRepository.findCarBasicByCarNumber(carNumber, userId);
        CarAddResponseDto carAddResponseDto = new CarAddResponseDto();

        if (!userId.equals(carBasic.getUser().getUserId())){
            carAddResponseDto.setMessage("소유자 정보가 일치하지 않습니다.");
        } else{
            carAddResponseDto.setMessage("소유자 정보 확인 완료");

        }

        carBasicRepository.updateCheck(carBasic.getCarId());

        return carAddResponseDto;
    }

    public List<MyCarBasicResponseDto> getMyCar(){
        Long userId = 1L;
        List<CarBasic> myCarBasicResponseDtoList = carBasicRepository.findAllByUserId(userId);
        List<MyCarBasicResponseDto> myCarList = new ArrayList<>();

        return null;
    }
}
