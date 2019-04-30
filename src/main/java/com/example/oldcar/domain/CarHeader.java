package com.example.oldcar.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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
    @ApiModelProperty("主键id，自增")
    private Long id;

    /**
     * 品牌
     */
    @ManyToOne
    @ApiModelProperty("品牌")
    private CarBrand brand;

    /**
     * 车系
     */
    @ManyToOne
    @ApiModelProperty("车系")
    private CarSeries series;

    /**
     * 车型名称
     */
    @ApiModelProperty("车型名称")
    private String name;

    /**
     * 车型图
     */
    @ManyToOne
    @ApiModelProperty("车型图")
    private FilePath picture;

    /**
     * 外观展示
     */
    @ApiModelProperty("外观展示")
    @ManyToOne
    private CarOutShow outShow;

    /**
     * 内饰展示
     */
    @ApiModelProperty("内饰展示")
    @OneToOne
    private CarInShow inShow;

    /**
     * 车型详情
     */
    @OneToOne
    @ApiModelProperty("车型详情")
    private CarDetail detail;

    /**
     * 购买价格
     */
    @ApiModelProperty("购买价格")
    private Double buyPrice;

    /**
     * 租赁价格
     */
    @ApiModelProperty("租赁价格")
    private Double leasePrice;

    /**
     * 级别 0,1,2,3... 分别表示紧凑型、小型车、中型车、SUV等
     */
    @ApiModelProperty("级别 0,1,2,3... 分别表示紧凑型、小型车、中型车、SUV等")
    private Integer level;

    /**
     * 分类 0,1,2,3   分别表示老爷车、平行进口车、二手车和新能源车
     */
    @ApiModelProperty("分类 0,1,2,3   分别表示老爷车、平行进口车、二手车和新能源车")
    private Integer type;

    /**
     * 年份 老爷车专有字段
     */
    @Temporal(value = TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty("年份 老爷车专有字段")
    private Date years;

    /**
     * 使用时长 /年  二手车专有字段
     */
    @ApiModelProperty("使用时长 /年  二手车专有字段")
    private Integer useLength;

    /**
     * 配置
     */
    @OneToMany(mappedBy = "car",cascade = CascadeType.ALL)
    @ApiModelProperty("配置 真皮座椅、倒车影像等")
    private List<CarConfig> config;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CarBrand getBrand() {
        return brand;
    }

    public void setBrand(CarBrand brand) {
        this.brand = brand;
    }

    public CarSeries getSeries() {
        return series;
    }

    public void setSeries(CarSeries series) {
        this.series = series;
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

    public Double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(Double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public Double getLeasePrice() {
        return leasePrice;
    }

    public void setLeasePrice(Double leasePrice) {
        this.leasePrice = leasePrice;
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

    public Date getYears() {
        return years;
    }

    public void setYears(Date years) {
        this.years = years;
    }

    public Integer getUseLength() {
        return useLength;
    }

    public void setUseLength(Integer useLength) {
        this.useLength = useLength;
    }

    public List<CarConfig> getConfig() {
        return config;
    }

    public void setConfig(List<CarConfig> config) {
        this.config = config;
    }

    public CarOutShow getOutShow() {
        return outShow;
    }

    public void setOutShow(CarOutShow outShow) {
        this.outShow = outShow;
    }

    public CarInShow getInShow() {
        return inShow;
    }

    public void setInShow(CarInShow inShow) {
        this.inShow = inShow;
    }

    public CarDetail getDetail() {
        return detail;
    }

    public void setDetail(CarDetail detail) {
        this.detail = detail;
    }
}
