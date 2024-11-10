package com.example.carmanager.car.repository;

import com.example.carmanager.car.entity.Maintance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaintanceRepository extends JpaRepository<Maintance, Long> {
}
