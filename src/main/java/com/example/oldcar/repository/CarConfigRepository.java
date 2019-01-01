package com.example.oldcar.repository;

import com.example.oldcar.domain.CarConfig;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 说明:
 *
 * @author WaveLee
 * 日期: 2018/12/28
 */
public interface CarConfigRepository extends JpaRepository<CarConfig,Long> {
}
