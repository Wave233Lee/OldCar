package com.example.oldcar.domain;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

/**
 * 说明:用户的车
 *
 * @author WaveLee
 * 日期: 2019/1/19
 */
@Entity
@Table(name = "user_garage")
public class UserGarage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("主键id，自增")
    private Long id;

    /**
     * 用户
     */
    @ManyToOne
    private User user;

    /**
     * 车
     */
    @ManyToOne
    private CarHeader car;

    /**
     * 车牌
     */
    private String plate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CarHeader getCar() {
        return car;
    }

    public void setCar(CarHeader car) {
        this.car = car;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }
}
