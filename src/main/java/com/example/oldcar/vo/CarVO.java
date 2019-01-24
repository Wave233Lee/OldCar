package com.example.oldcar.vo;

import com.example.oldcar.domain.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * 说明:
 *
 * @author WaveLee
 * 日期: 2019/1/24
 */
public class CarVO {
    private Long id;

    /**
     * 品牌
     */
    @ManyToOne
    private CarBrand brand;

    /**
     * 车系
     */
    @ManyToOne
    private CarSeries series;

    /**
     * 车型名称
     */
    private String name;

    /**
     * 车型图
     */
    @ManyToOne
    private FilePath picture;

    /**
     * 外观展示
     */
    @ManyToOne
    private CarOutShow outShow;

    /**
     * 内饰展示
     */
    @OneToOne
    private CarInShow inShow;

    /**
     * 车型详情
     */
    @ManyToOne
    private CarDetail detail;

    /**
     * 购买价格
     */
    private Integer BuyPrice;

    /**
     * 租赁价格
     */
    private Integer LeasePrice;

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
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date years;

    /**
     * 使用时长 /年  二手车专有字段
     */
    private Integer useLength;

    /**
     * 配置
     */
    @ManyToOne
    private CarConfig config;

    /**
     * PR值
     */
    private Integer pr;

    public CarVO(CarHeader car,Integer pr){
        this.pr = pr;
        this.brand = car.getBrand();
    }
}
