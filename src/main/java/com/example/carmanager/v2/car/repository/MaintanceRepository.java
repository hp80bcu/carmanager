package com.example.carmanager.v2.car.repository;

import com.example.carmanager.v2.car.entity.Maintance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaintanceRepository extends JpaRepository<Maintance, Long> {
}
