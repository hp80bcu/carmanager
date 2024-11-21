package com.example.carmanager.v2.pay.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Table(name = "payment_management")
public class PaymentManage {
    @Id
    @Column(name = "user_payment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long userPaymentId;

    @Column(name = "user_id")
    Long userId;

    @Column(name = "payment_name")
    String paymentName;

    @Column(name = "payment_number")
    String paymentNumber;

    @UpdateTimestamp    // 현재시간 디폴트값
    @Column(name="createAt", updatable = false) // insert시 최초 시간만 넣고 시간 수정 안되게
    Timestamp createAt;
}
