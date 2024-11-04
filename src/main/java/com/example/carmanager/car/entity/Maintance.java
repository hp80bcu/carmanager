package com.example.carmanager.car.entity;

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
@Table(name = "option_kind")
public class Maintance {
    @Id
    @Column(name = "maintanceId")
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

//    @OneToMany(mappedBy = "carbasic")
//    private List<CarBasic> carBasics = new ArrayList<>();
}
