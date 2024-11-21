package com.example.carmanager.v2.pay.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Table(name = "payment_kind")
public class PaymentKind {
    @Id
    @Column(name = "payment_kind_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long userPaymentId;

    @Column(name = "payment")
    String payment;
}
