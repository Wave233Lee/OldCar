package com.example.oldcar.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * 说明: 卖车订单
 *
 * @author WaveLee
 * 日期: 2019/1/19
 */
@Entity
@Table(name = "car_sell_order")
public class SellCar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("主键id，自增")
    private Long id;

    /**
     * 售卖车型
     */
    @ManyToOne
    private CarHeader car;

    /**
     * 年份
     */
    @Temporal(value = TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty("年份")
    private Date years;

    /**
     * 公里数
     */
    @ApiModelProperty("公里数")
    private Double kilometers;

    /**
     * 是否交易买卖 0否/1是
     */
    @ApiModelProperty("是否交易买卖 0否/1是")
    private Integer buyFlag;

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
     * 期望卖价
     */
    @ApiModelProperty("期望卖价")
    private Integer BuyPrice;

    /**
     * 车型简介
     */
    @ApiModelProperty("车型简介")
    private String Introduction;

    /**
     * 车型详情
     */
    @ApiModelProperty("车型详情")
    @ManyToOne
    private CarDetail detail;

    /**
     * 车辆所在地
     */
    @ApiModelProperty("车辆所在地")
    private String location;

    /**
     * 车主电话
     */
    @ApiModelProperty("车主电话")
    private String tel;

    /**
     * 发布者
     */
    @ApiModelProperty("发布者")
    @ManyToOne
    private User publisher;

    /**
     * 订单状态 0待审核/1已审核
     */
    @ApiModelProperty("订单状态")
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CarHeader getCar() {
        return car;
    }

    public void setCar(CarHeader car) {
        this.car = car;
    }

    public Date getYears() {
        return years;
    }

    public void setYears(Date years) {
        this.years = years;
    }

    public Double getKilometers() {
        return kilometers;
    }

    public void setKilometers(Double kilometers) {
        this.kilometers = kilometers;
    }

    public Integer getBuyFlag() {
        return buyFlag;
    }

    public void setBuyFlag(Integer buyFlag) {
        this.buyFlag = buyFlag;
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

    public Integer getBuyPrice() {
        return BuyPrice;
    }

    public void setBuyPrice(Integer buyPrice) {
        BuyPrice = buyPrice;
    }

    public String getIntroduction() {
        return Introduction;
    }

    public void setIntroduction(String introduction) {
        Introduction = introduction;
    }

    public CarDetail getDetail() {
        return detail;
    }

    public void setDetail(CarDetail detail) {
        this.detail = detail;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public User getPublisher() {
        return publisher;
    }

    public void setPublisher(User publisher) {
        this.publisher = publisher;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
