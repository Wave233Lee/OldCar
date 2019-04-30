package com.example.oldcar.repository;

import com.example.oldcar.domain.BuyCar;
import com.example.oldcar.domain.CarBrand;
import com.example.oldcar.domain.CarSeries;
import com.example.oldcar.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BuyCarRepository extends JpaRepository<BuyCar,Long> {
    List<BuyCar> findByBuyer(User user);
    List<BuyCar> findByCar_Brand(CarBrand brand);
    List<BuyCar> findByCar_Series(CarSeries series);
    List<BuyCar> findByCar_Level(Integer level);
    List<BuyCar> findByCar_PriceRange(Integer priceRange);
    List<BuyCar> findByCar_UseLengthRange(Integer useLengthRange);
    List<BuyCar> findByBuyerAndCar_Brand(User user, CarBrand brand);

//    List<BuyCar> findByCar_Series(CarSeries series);
//    List<BuyCar> findByCar_Level(Integer level);
//    List<BuyCar> findByCar_PriceRange(Integer priceRange);
//    List<BuyCar> findByCar_UseLengthRange(Integer useLengthRange);
    BuyCar findFirstByOrderByIdDesc();
    BuyCar findFirstByBuyerOrderByIdDesc(User user);
}
