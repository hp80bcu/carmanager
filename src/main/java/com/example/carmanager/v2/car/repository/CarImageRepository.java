package com.example.carmanager.v2.car.repository;


import com.example.carmanager.v2.car.entity.CarImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarImageRepository extends JpaRepository<CarImage, Long> {
    @Query("SELECT c FROM CarImage c WHERE c.carId = :carId")
    List<CarImage> findProfileImageByCarId(@Param("carId") Long carId);
    @Query("SELECT c FROM CarImage c WHERE c.carId = :carId")
    List<CarImage> findImageByCarId(@Param("carId") Long carId);
}
