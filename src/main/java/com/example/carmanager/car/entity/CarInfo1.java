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
@Table(name = "carbasic")
public class CarInfo1 {
    @Id
    @Column(name = "carinfo1")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carInfo1Id;

    @Column(name = "weight")
    private Integer weight;

    @Column(name = "length")
    private Integer length;

    @Column(name = "width")
    private Integer width;

    @Column(name = "height")
    private Integer height;

//    @OneToMany(mappedBy = "carbasic")
//    private List<CarBasic> carBasics = new ArrayList<>();
}
