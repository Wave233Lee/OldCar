package com.example.oldcar.repository;

import com.example.oldcar.domain.Ad;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 说明:
 *
 * @author WaveLee
 * 日期: 2018/12/25
 */
public interface AdRepository extends JpaRepository<Ad,Long> {
    /**
     * 通过投放位置查询
     */
    Ad findByLocation(String location);
}
