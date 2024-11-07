package com.example.carmanager.car.service;

import com.example.carmanager.car.dto.CarAddRequestDto;
import com.example.carmanager.car.dto.CarAddResponseDto;
import com.example.carmanager.car.dto.MyCarBasicResponseDto;
import com.example.carmanager.car.entity.CarBasic;
import com.example.carmanager.car.entity.CarInfo2;
import com.example.carmanager.car.repository.CarBasicRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
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

    public List<MyCarBasicResponseDto> getMyCar() {
        Long userId = 1L;
        List<CarBasic> carBasicList = carBasicRepository.findAllByUserId(userId);
        List<MyCarBasicResponseDto> responseDtoList = new ArrayList<>();

        for (CarBasic carBasic : carBasicList) {
            MyCarBasicResponseDto dto = new MyCarBasicResponseDto();
            dto.setCarNum(carBasic.getCarNumber());
            dto.setCompany(carBasic.getCarType());
            dto.setModel(carBasic.getModelName());
            dto.setModelDetail(carBasic.getModelDetail());
            dto.setDate(carBasic.getModelYear());
            dto.setDistance(carBasic.getDistance());
            dto.setColor(carBasic.getCarType());
            dto.setFirstRegisterDate(new Timestamp(carBasic.getModelYear().getTime()));
            dto.setFuel(carBasic.getFuel());

            // CarInfo2 정보 매핑
            CarInfo2 carInfo2 = carBasic.getCarInfo2();
            if (carInfo2 != null) {
                dto.setDisplacement(carInfo2.getDisplacement().doubleValue());
                dto.setShift(carInfo2.getShift());
            } else {
                dto.setDisplacement(null);
                dto.setShift(null);
            }

            responseDtoList.add(dto);
        }

        return responseDtoList;
    }
}
