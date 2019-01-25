package com.example.oldcar.vo;

import com.example.oldcar.domain.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * 说明:汽车VO对象
 *
 * @author WaveLee
 * 日期: 2019/1/24
 */
public class CarVO {
    private Long id;

    /**
     * 品牌
     */
    private CarBrand brand;

    /**
     * 车系
     */
    private CarSeries series;

    /**
     * 车型名称
     */
    private String name;

    /**
     * 车型图
     */
    private FilePath picture;

    /**
     * 外观展示
     */
    private CarOutShow outShow;

    /**
     * 内饰展示
     */
    private CarInShow inShow;

    /**
     * 车型详情
     */
    private CarDetail detail;

    /**
     * 购买价格
     */
    private Double buyPrice;

    /**
     * 租赁价格
     */
    private Double leasePrice;

    /**
     * 级别 0,1,2,3... 分别表示紧凑型、小型车、中型车、SUV等
     */
    private Integer level;

    /**
     * 分类 0,1,2,3   分别表示老爷车、平行进口车、二手车和新能源车
     */
    private Integer type;

    /**
     * 年份 老爷车专有字段
     */
    @Temporal(value = TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date years;

    /**
     * 使用时长 /年  二手车专有字段
     */
    private Integer useLength;

    /**
     * 配置
     */
    private CarConfig config;

    /**
     * PR值
     */
    private Integer pr;

    public CarVO(CarHeader car, Integer pr) {
        this.id = car.getId();
        this.brand = car.getBrand();
        this.series = car.getSeries();
        this.name = car.getName();
        this.picture = car.getPicture();
        this.outShow = car.getOutShow();
        this.inShow = car.getInShow();
        this.detail = car.getDetail();
        this.buyPrice = car.getBuyPrice();
        this.leasePrice = car.getLeasePrice();
        this.level = car.getLevel();
        this.type = car.getType();
        this.years = car.getYears();
        this.useLength = car.getUseLength();
        this.config = car.getConfig();
        this.pr = pr;
    }
}
