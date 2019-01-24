package com.example.oldcar.repository;

import com.example.oldcar.domain.CarBrand;
import com.example.oldcar.domain.CarHeader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 说明:
 *
 * @author WaveLee
 * 日期: 2018/12/27
 */
public interface CarHeaderRepository extends JpaRepository<CarHeader,Long> {
    /**
     * 通过汽车品牌查询
     */
    Page<CarHeader> findByBrand_Id(Long brand, Pageable pageable);

    /**
     * 通过汽车级别查询
     */
    Page<CarHeader> findByLevel(Integer level,Pageable pageable);

    /**
     * 通过价格区间查询
     */
    Page<CarHeader> findByBuyPriceBetween(Integer start,Integer end,Pageable pageable);
}
