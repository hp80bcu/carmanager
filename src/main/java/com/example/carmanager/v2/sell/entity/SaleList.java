package com.example.carmanager.v2.sell.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Table(name = "salelist")
public class SaleList {
    @Id
    @Column(name = "salelist_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long saleListId;

    @Column(name = "car_id")
    private Long carId;

    @Column(name = "options")
    private String options;

    @Column(name = "price")
    private int price;

    @Column(name = "car_description")
    private String carDescription;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "createAt")
    private Timestamp createAt;

    @Column(name = "modifiedAt")
    private Timestamp modifiedAt;
}
