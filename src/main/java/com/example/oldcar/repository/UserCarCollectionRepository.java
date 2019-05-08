package com.example.oldcar.repository;

import com.example.oldcar.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 说明:
 *
 * @author WaveLee
 * 日期: 2019/5/2
 */
public interface UserCarCollectionRepository extends JpaRepository<UserCarCollection,Long> {
    List<UserCarCollection> findByUser(User user);
    List<UserCarCollection> findByUserAndCar_Brand(User user, CarBrand brand);
    List<UserCarCollection> findByUserAndCar_Level_Id(User user, Integer level);
    List<UserCarCollection> findByUserAndCar_PriceRange(User user, Integer priceRange);
    List<UserCarCollection> findByUserAndCar_UseLengthRange(User user, Integer useLengthRange);
    UserCarCollection findFirstByUserOrderByIdDesc(User user);
}
