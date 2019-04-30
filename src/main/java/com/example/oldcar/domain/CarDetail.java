package com.example.oldcar.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

/**
 * 说明:车型详情介绍 长文本
 *
 * @author WaveLee
 * 日期: 2019/1/19
 */
@Entity
@Table(name = "common_car_detail")
public class CarDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("主键id，自增")
    private Long id;

    /**
     * 对应车型
     */
    @JsonIgnore
    @OneToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH},fetch=FetchType.LAZY, mappedBy = "detail")
    private CarHeader car;

    /**
     * 长文本
     */
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "text")
    private String description;

    public CarHeader getCar() {
        return car;
    }

    public void setCar(CarHeader car) {
        this.car = car;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
