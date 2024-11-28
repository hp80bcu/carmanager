package com.example.carmanager.v2.car.service;


import com.example.carmanager.v2.car.dto.*;
import com.example.carmanager.v2.car.entity.CarBasic;
import com.example.carmanager.v2.car.entity.CarInfo2;
import com.example.carmanager.v2.car.entity.Maintance;
import com.example.carmanager.v2.car.repository.CarBasicRepository;
import com.example.carmanager.v2.car.repository.MaintanceRepository;
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
    private final MaintanceRepository maintanceRepository;

    public CarNumberAddResponseDto getCarByCarNumber(CarNumberAddRequestDto carNumberAddRequestDto){
        String carNumber = carNumberAddRequestDto.getCarNumber();
        CarBasic carBasic = carBasicRepository.findCarByCarNum(carNumber);
        CarNumberAddResponseDto carNumberAddResponseDto = new CarNumberAddResponseDto();
        carNumberAddResponseDto.setCompany(carBasic.getCompany());
        carNumberAddResponseDto.setModel(carBasic.getModelName());
        carNumberAddResponseDto.setDetail(carBasic.getModelDetail());
        carNumberAddResponseDto.setImage(carBasic.getProfileImage());

        return carNumberAddResponseDto;
    }

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

    /**
     * 내 차량 정보 모두 조회
     * @return
     */
    public List<MyCarBasicResponseDto> getMyCar(Long userId) {
        List<CarBasic> carBasicList = carBasicRepository.findAllByUserId(userId);
        List<MyCarBasicResponseDto> responseDtoList = new ArrayList<>();

        for (CarBasic carBasic : carBasicList) {
            MyCarBasicResponseDto dto = new MyCarBasicResponseDto();
            dto.setCarId(carBasic.getCarId());
            dto.setImage(carBasicRepository.findImage(carBasic.getCarId()));
            dto.setCarNum(carBasic.getCarNumber());
            dto.setCompany(carBasic.getCarType());
            dto.setModel(carBasic.getModelName());
            dto.setModelDetail(carBasic.getModelDetail());
            dto.setDate(carBasic.getModelYear());
            dto.setDistance(carBasic.getDistance());
            dto.setColor(carBasic.getCarType());
            dto.setFirstRegisterDate(new Timestamp(carBasic.getModelYear().getTime()));
            dto.setFuel(carBasic.getFuel());
            if(carBasic.getIsSale() == 1){
                dto.setIsSale(1);
            } else {
                dto.setIsSale(0);
            }

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

    public List<CarMaintanceResponseDto> getMyCarMaintance(Long carId){
        List<Maintance> maintanceList = maintanceRepository.findAllMaintanceByCarId(carId);
        String carNum = carBasicRepository.findCarNumByCarId(carId);

        List<CarMaintanceResponseDto> responseDtoList = new ArrayList<>();

        for (Maintance maintance : maintanceList) {
            CarMaintanceResponseDto carMaintanceResponsedto = new CarMaintanceResponseDto();
            carMaintanceResponsedto.setCarNum(carNum);
            carMaintanceResponsedto.setMaintanceDate(maintance.getMaintanceDate());
            carMaintanceResponsedto.setMaintanceCompany(maintance.getMaintanceCompany());
            carMaintanceResponsedto.setPerformanceCheckDistance(maintance.getPerformanceCheckDistance());
            carMaintanceResponsedto.setContent(maintance.getContent());

            responseDtoList.add(carMaintanceResponsedto);
        }

        return responseDtoList;
    }
}
