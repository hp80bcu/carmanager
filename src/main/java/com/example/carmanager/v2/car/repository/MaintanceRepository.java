package com.example.carmanager.v2.car.repository;

import com.example.carmanager.v2.car.entity.CarBasic;
import com.example.carmanager.v2.car.entity.Maintance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MaintanceRepository extends JpaRepository<Maintance, Long> {
    @Query("SELECT m FROM Maintance m WHERE m.carBasic.carId = :carId")
    List<Maintance> findAllMaintanceByCarId(@Param("carId")Long carId);
}
