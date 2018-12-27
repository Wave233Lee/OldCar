package com.example.oldcar.domain;

import javax.persistence.*;

/**
 * 说明: 汽车基本信息   部分字段待设计分离存储
 *
 * @author WaveLee
 * 日期: 2018/12/25
 */
@Entity
@Table(name = "common_car_header")
public class CarHeader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 品牌
     */
    @ManyToOne
    private CarBrand carBrand;

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
     * 价格
     */
    private Integer price;

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
    private Integer year;

    /**
     * 使用时长 /年  二手车专有字段
     */
    private Integer useLength;

    /**
     * 配置 以数值量化
     */
    private Integer config;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CarBrand getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(CarBrand carBrand) {
        this.carBrand = carBrand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FilePath getPicture() {
        return picture;
    }

    public void setPicture(FilePath picture) {
        this.picture = picture;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getUseLength() {
        return useLength;
    }

    public void setUseLength(Integer useLength) {
        this.useLength = useLength;
    }

    public Integer getConfig() {
        return config;
    }

    public void setConfig(Integer config) {
        this.config = config;
    }
}
