package com.example.carmanager.v2.sell.service;

import com.example.carmanager.v2.sell.dto.SellAddRequestDto;
import com.example.carmanager.v2.sell.dto.SellAddResponseDto;
import com.example.carmanager.v2.sell.repository.SaleListRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class SaleListService {
    private final SaleListRepository saleListRepository;

    // 판매리스트 등록
    public SellAddResponseDto sellAddResponseDto(SellAddRequestDto sellAddRequestDto){
        String outerOptions = String.join(", ", sellAddRequestDto.getOuterOptions());
        String innerOptions = String.join(", ", sellAddRequestDto.getInnerOptions());
        String safetyOptions = String.join(", ", sellAddRequestDto.getSafetyOptions());
        String multipleOptions = String.join(", ", sellAddRequestDto.getMultipleOptions());


        return null;
    }
}
