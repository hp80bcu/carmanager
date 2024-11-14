package com.example.carmanager.v2.sell.repository;

import com.example.carmanager.v2.sell.entity.SaleList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleListRepository extends JpaRepository<SaleList, Long> {
}
