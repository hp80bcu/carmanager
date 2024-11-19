package com.example.carmanager.v2.car.repository;

import com.example.carmanager.v2.car.entity.CarInfo1;
import com.example.carmanager.v2.car.entity.CarInfo2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CarInfo2Repository extends JpaRepository<CarInfo2, Long> {
}
