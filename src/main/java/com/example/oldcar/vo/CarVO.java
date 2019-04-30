package com.example.oldcar.vo;

import com.example.oldcar.domain.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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
    private List<CarConfig> configs;

    /**
     * PR值
     */
    private Integer pr;

    public CarVO() {
    }

    public CarVO(Long id, CarBrand brand, CarSeries series, String name, FilePath picture, CarOutShow outShow, CarInShow inShow, CarDetail detail, Double buyPrice, Double leasePrice, Integer level, Integer type, Date years, Integer useLength, List<CarConfig> configs, Integer pr) {
        this.id = id;
        this.brand = brand;
        this.series = series;
        this.name = name;
        this.picture = picture;
        this.outShow = outShow;
        this.inShow = inShow;
        this.detail = detail;
        this.buyPrice = buyPrice;
        this.leasePrice = leasePrice;
        this.level = level;
        this.type = type;
        this.years = years;
        this.useLength = useLength;
        this.configs = configs;
        this.pr = pr;
    }
}
