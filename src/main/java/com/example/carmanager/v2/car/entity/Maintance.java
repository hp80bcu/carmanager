package com.example.carmanager.v2.car.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Table(name = "maintance")
public class Maintance {
    @Id
    @Column(name = "maintance_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maintanceId;

    @Column(name = "performance_check_distance")
    private Double performanceCheckDistance;

    @Column(name = "maintance_company")
    private String maintanceCompany;

    @Column(name = "maintance_date")
    private Date maintanceDate;

    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private CarBasic carBasic;
}
