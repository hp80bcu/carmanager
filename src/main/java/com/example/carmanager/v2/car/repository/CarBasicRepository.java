package com.example.carmanager.v2.car.repository;


import com.example.carmanager.v2.car.entity.CarBasic;
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

    @Query("SELECT c.carNumber FROM CarBasic c WHERE c.carId = :carId")
    String findCarNumByCarId(@Param("carId") Long carId);

    @Query("SELECT c FROM CarBasic c WHERE c.carId = :carId")
    CarBasic findCarByCarId(@Param("carId") Long carId);

    @Query("SELECT c FROM CarBasic c WHERE c.carNumber = :carNumber")
    CarBasic findCarByCarNum(@Param("carNumber") String carNumber);

    @Modifying
    @Query("UPDATE CarBasic c set c.isSale = 1 WHERE c.carId = :carId")
    void updateSale(@Param("carId")Long carId);

    @Query("SELECT c FROM CarBasic c WHERE c.isSale = 1")
    List<CarBasic> findAllByIsSaleChecked();

    @Query("SELECT COUNT(c) FROM CarBasic c WHERE c.isSale = 1")
    int countAllCars();

    @Query("SELECT COUNT(c) FROM CarBasic c WHERE c.isSale = 1 And c.company = :company And c.modelName = :model And c.modelDetail = :detail ")
    int countAllCarsFilterByCompanyAndModelAndDetail(@Param("company") String company, @Param("model") String model, @Param("detail") String detail);

    @Query("SELECT c.profileImage FROM CarBasic c WHERE c.carId = :carId")
    String findImage(@Param("carId") Long carId);
}
