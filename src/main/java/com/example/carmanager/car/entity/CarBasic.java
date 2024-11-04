package com.example.carmanager.car.entity;

import com.example.carmanager.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Table(name = "carbasic")
public class CarBasic {
    @Id
    @Column(name = "car_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "car_number")
    private String carNumber;

    @Column(name = "model_name")
    private String modelName;

    @Column(name = "carType")
    private String carType;

    @Column(name = "modelYear")
    private Date modelYear;

    @Column(name = "distance")
    private Integer distance;

    @ManyToOne
    @JoinColumn(name = "carinfo1_id")
    private CarInfo1 carInfo1;

    @ManyToOne
    @JoinColumn(name = "carinfo2_id")
    private CarInfo2 carInfo2;

    @ManyToOne
    @JoinColumn(name = "maintance_id")
    private Maintance maintance;

    @ManyToOne
    @JoinColumn(name = "file_id")
    private CarImage carImage;

    @ManyToOne
    @JoinColumn(name = "option_id")
    private Option option;
}
