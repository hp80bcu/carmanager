package com.example.carmanager.car.repository;

import com.example.carmanager.car.entity.CarImage;
import com.example.carmanager.car.entity.CarInfo2;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarImageRepository extends JpaRepository<CarImage, Long> {
}
