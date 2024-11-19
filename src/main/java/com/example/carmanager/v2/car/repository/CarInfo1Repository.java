package com.example.carmanager.v2.car.repository;


import com.example.carmanager.v2.car.entity.CarImage;
import com.example.carmanager.v2.car.entity.CarInfo1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarInfo1Repository extends JpaRepository<CarInfo1, Long> {
}
