package com.example.oldcar.domain;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

/**
 * 说明:买车订单
 *
 * @author WaveLee
 * 日期: 2019/1/19
 */
@Entity
@Table(name = "car_buy_order")
public class BuyCar{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("主键id，自增")
    private Long id;

    /**
     * 订单号 后台生成
     */
    @ApiModelProperty("订单号 后台生成")
    private String orderNo;

    /**
     * 车
     */
    @ApiModelProperty("所购车")
    @ManyToOne
    private CarHeader car;

    /**
     * 购买者
     */
    @ApiModelProperty("购买者")
    @ManyToOne
    private User buyer;

    /**
     * 订单状态 0待付款/1待发货/2待收货/3待评价/4待售后
     */
    @ApiModelProperty("订单状态 0待付款/1待发货/2待收货/3待评价/4待售后")
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

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
