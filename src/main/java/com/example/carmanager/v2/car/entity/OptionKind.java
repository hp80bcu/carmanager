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
@Table(name = "option_kind")
public class OptionKind {
    @Id
    @Column(name = "option_kind_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long optionKindId;

    @Column(name = "kind")
    private String kind;

//    @OneToMany(mappedBy = "carbasic")
//    private List<CarBasic> carBasics = new ArrayList<>();
}
