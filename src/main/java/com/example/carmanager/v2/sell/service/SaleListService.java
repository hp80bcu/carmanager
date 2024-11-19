package com.example.carmanager.v2.sell.service;

import com.example.carmanager.v2.car.dto.MyCarBasicResponseDto;
import com.example.carmanager.v2.car.entity.CarBasic;
import com.example.carmanager.v2.car.entity.CarImage;
import com.example.carmanager.v2.car.entity.CarInfo2;
import com.example.carmanager.v2.car.repository.CarBasicRepository;
import com.example.carmanager.v2.car.repository.CarImageRepository;
import com.example.carmanager.v2.s3.service.S3UploadService;
import com.example.carmanager.v2.sell.dto.SellAddRequestDto;
import com.example.carmanager.v2.sell.dto.SellAddResponseDto;
import com.example.carmanager.v2.sell.dto.SellListResponseDto;
import com.example.carmanager.v2.sell.entity.SaleList;
import com.example.carmanager.v2.sell.repository.SaleListRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class SaleListService {
    private final SaleListRepository saleListRepository;
    private final CarImageRepository carImageRepository;
    // private final OptionRepository optionRepository;
    private final CarBasicRepository carBasicRepository;
    private final S3UploadService s3UploadService;

    // 판매리스트 등록
    public SellAddResponseDto addCar(SellAddRequestDto sellAddRequestDto) throws IOException {
        String outerOptions = String.join(", ", sellAddRequestDto.getOuterOptions());
        String innerOptions = String.join(", ", sellAddRequestDto.getInnerOptions());
        String safetyOptions = String.join(", ", sellAddRequestDto.getSafetyOptions());
        String multipleOptions = String.join(", ", sellAddRequestDto.getMultipleOptions());

        String options = outerOptions + ", " + innerOptions + ", " + safetyOptions + ", " + multipleOptions;

        SellAddResponseDto sellAddResponseDto = new SellAddResponseDto();

        // 판매리스트 정보 등록
        SaleList saleList = new SaleList();
        saleList.setCarDescription(sellAddRequestDto.getDescription());
        saleList.setOptions(options);
        saleList.setCarId(sellAddRequestDto.getCarId());
        saleList.setUserId(1L);
        saleListRepository.save(saleList);
        carBasicRepository.updateSale(sellAddRequestDto.getCarId());
        sellAddResponseDto.setCarId(sellAddRequestDto.getCarId());
        return sellAddResponseDto;
    }

    // 차량 사진 업로드
    @Transactional
    public List<String> uploadCarImages(Long carId, List<MultipartFile> pictures) throws IOException {
        List<String> fileUrls = new ArrayList<>();

        for (MultipartFile picture : pictures) {
            // S3에 파일 업로드 후 URL 반환
            String fileUrl = s3UploadService.saveFile(picture);
            fileUrls.add(fileUrl);

            // DB에 각 파일 URL 저장
            CarImage carImage = new CarImage();
            carImage.setCarId(carId);
            carImage.setFile(fileUrl);
            carImageRepository.save(carImage);
        }

        return fileUrls;
    }

    // 판매 등록된 모든 차량 가져오기
    public List<SellListResponseDto> getAllCars(){
        List<CarBasic> carBasicList = carBasicRepository.findAllByIsSaleChecked();
        List<SaleList> saleList = saleListRepository.findAll();
        List<SellListResponseDto> responseDtoList = new ArrayList<>();
        int countAllCars = carBasicRepository.countAllCars();

        for (CarBasic carBasic  : carBasicList) {
            SellListResponseDto dto = new SellListResponseDto();
            for(SaleList sale : saleList){
                dto.setHowManyCar(countAllCars);
                dto.setModel(carBasic.getModelName());
                dto.setDistance(carBasic.getDistance());
                dto.setFuel(carBasic.getFuel());
                dto.setPrice(sale.getPrice());
                dto.setCarId(sale.getCarId());
                dto.setRegion(carBasic.getRegion());
                dto.setYear(carBasic.getModelYear());
            }
            responseDtoList.add(dto);
        }
        return responseDtoList;
    }
}
