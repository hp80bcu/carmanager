package com.example.carmanager.v2.pay.repository;

import com.example.carmanager.v2.pay.entity.PaymentKind;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentKindRepository extends JpaRepository<PaymentKind, Long> {
}
