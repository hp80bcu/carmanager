package com.example.carmanager.v2.sell.repository;

import com.example.carmanager.v2.car.entity.CarInfo1;
import com.example.carmanager.v2.sell.entity.SaleList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleListRepository extends JpaRepository<SaleList, Long> {
    @Query("SELECT s FROM SaleList s WHERE s.carId = :carId")
    SaleList findSalelist1ByCarId(@Param("carId") Long carId);

    @Query("SELECT s FROM SaleList s " +
            "JOIN FETCH s.carBasic c " +
            "WHERE c.company = :company " +
            "AND c.modelName = :model " +
            "AND c.modelDetail = :detail " +
            "AND c.isSale = 1")
    List<SaleList> findSaleListFilterByCompanyAndModelAndDetail(
            @Param("company") String company,
            @Param("model") String model,
            @Param("detail") String detail);



}
