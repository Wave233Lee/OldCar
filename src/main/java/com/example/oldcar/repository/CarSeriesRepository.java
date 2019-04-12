package com.example.oldcar.repository;

import com.example.oldcar.domain.CarSeries;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 说明:
 *
 * @author WaveLee
 * 日期: 2019/4/12
 */
public interface CarSeriesRepository extends JpaRepository<CarSeries,Long> {

}
