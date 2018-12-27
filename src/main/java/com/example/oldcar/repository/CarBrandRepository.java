package com.example.oldcar.repository;

import com.example.oldcar.domain.CarBrand;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 说明:
 *
 * @author WaveLee
 * 日期: 2018/12/27
 */
public interface CarBrandRepository extends JpaRepository<CarBrand,Long> {
}
