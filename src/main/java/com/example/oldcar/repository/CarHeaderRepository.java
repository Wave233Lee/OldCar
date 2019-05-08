package com.example.oldcar.repository;

import com.example.oldcar.domain.CarBrand;
import com.example.oldcar.domain.CarHeader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * 说明:
 *
 * @author WaveLee
 * 日期: 2018/12/27
 */
public interface CarHeaderRepository extends JpaRepository<CarHeader,Long> {
    /**
     * 推荐需要的精确查询
     */
    List<CarHeader> findByBrand_IdAndLevelAndPriceRangeAndUseLengthRange(Long id,Integer level,Integer pricerange,Integer uselengthrange);
    List<CarHeader> findByBrand_IdAndLevelNotAndPriceRangeAndUseLengthRange(Long id,Integer level,Integer pricerange,Integer uselengthrange);
    List<CarHeader> findByBrand_IdAndLevelNotAndPriceRangeNotAndUseLengthRange(Long id,Integer level,Integer pricerange,Integer uselengthrange);
    List<CarHeader> findByBrand_IdAndLevelNotAndPriceRangeNotAndUseLengthRangeNot(Long id,Integer level,Integer pricerange,Integer uselengthrange);
    List<CarHeader> findFirst10ByBrand_IdNotOrderByUseLengthRange(Long id);
    List<CarHeader> findFirst10ByOrderByUseLengthRange();


    /**
     * 通过汽车品牌查询
     */
    Page<CarHeader> findByBrand_Id(Long brand, Pageable pageable);
    List<CarHeader> findByBrand_Id(Long brand);

    /**
     * 通过汽车名称模糊查询
     */
    List<CarHeader> findByNameLike(String name);

    /**
     * 通过汽车品牌名称模糊查询
     */
    List<CarHeader> findByBrand_brandNameLike(String keyWord);

    /**
     * 通过汽车配置名称模糊查询
     */
//    List<CarHeader> findByConfig_NameLike(String keyWord);

    /**
     * 分类查询
     */
    List<CarHeader> findByTypeAndNameLike(Integer type,String keyWord);


    /**
     * 通过汽车级别查询
     */
    Page<CarHeader> findByLevel(Integer level,Pageable pageable);
    List<CarHeader> findByLevel(Integer level);

    /**
     * 通过价格区间查询
     */
    Page<CarHeader> findByBuyPriceBetween(Double start,Double end,Pageable pageable);
    List<CarHeader> findByBuyPriceBetween(Double start,Double end);

    /**
     * 通过年份区间查询
     */
    Page<CarHeader> findByYearsBetween(Date start, Date end, Pageable pageable);
    List<CarHeader> findByYearsBetween(Date start,Date end);
}
