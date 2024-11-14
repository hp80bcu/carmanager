package com.example.carmanager.v2.car.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Table(name = "car_image")
public class CarImage {
    @Id
    @Column(name = "file_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fileId;

    @Column(name = "car_id")
    private Long carId;

    @Column(name = "file")
    private String file;

    @Column(name = "createAt")
    private Timestamp createAt;

//    @OneToMany(mappedBy = "carbasic")
//    private List<CarBasic> carBasics = new ArrayList<>();
}
