package com.example.carmanager.car.repository;

import com.example.carmanager.car.entity.CarBasic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarBasicRepository extends JpaRepository<CarBasic, Long> {

    @Query("SELECT c FROM CarBasic c WHERE c.carNumber = :carNumber AND c.user.userId = :userId")
    CarBasic findCarBasicByCarNumber( @Param("carNumber")String carNumber, @Param("userId")Long userId);

    @Modifying
    @Query("UPDATE CarBasic c set c.isChecked = 1 WHERE c.carId = :carId")
    void updateCheck(@Param("carId")Long carId);

    @Query("SELECT c FROM CarBasic c WHERE c.user.userId = :userId AND c.isChecked = 1")
    List<CarBasic> findAllByUserId(@Param("userId") Long userId);
}
