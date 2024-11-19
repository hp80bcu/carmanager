package com.example.carmanager.v2.car.entity;

import com.example.carmanager.v2.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@ToString(exclude = "carBasic")  // carBasic 필드 제외
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

    @Column(name = "region")
    private String region;

    @Column(name = "model_detail")
    private String modelDetail;

    @Column(name = "fuel")
    private String fuel;

    @Column(name = "car_type")
    private String carType;

    @Column(name = "model_year")
    private Date modelYear;

    @Column(name = "distance")
    private Integer distance;

    @Column(name = "isChecked")
    private Integer isChecked;

    @Column(name = "isSale")
    private Integer isSale;
//    @ManyToOne
//    @JoinColumn(name = "carinfo1_id")
//    private CarInfo1 carInfo1;
//
    @ManyToOne
    @JoinColumn(name = "carinfo2_id")
    private CarInfo2 carInfo2;

    @OneToMany(mappedBy = "carBasic")
    private List<Maintance> maintance = new ArrayList<>();
//
//    @ManyToOne
//    @JoinColumn(name = "maintance_id")
//    private Maintance maintance;
//
//    @ManyToOne
//    @JoinColumn(name = "file_id")
//    private CarImage carImage;
//
//    @ManyToOne
//    @JoinColumn(name = "option_id")
//    private Option option;
}
