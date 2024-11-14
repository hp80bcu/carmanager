package com.example.carmanager.v2.car.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Table(name = "carinfo2")
public class CarInfo2 {
    @Id
    @Column(name = "carinfo2_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carInfo2Id;

    @Column(name = "displacement")
    private Integer displacement;

    @Column(name = "fuel_efficiency")
    private Double fuelEfficiency;

    @Column(name = "shift")
    private String shift;

    @Column(name = "highway_fuel_efficiency")
    private Double highwayFuelEfficiency;

    @Column(name = "city_fuel_efficiency")
    private Double cityFuelEfficiency;

    @Column(name = "electric_efficiency")
    private Double electricEfficiency;

    @Column(name = "highway_electric_efficiency")
    private Double highwayElectricEfficiency;

//    @OneToMany(mappedBy = "carbasic")
//    private List<CarBasic> carBasics = new ArrayList<>();
}
