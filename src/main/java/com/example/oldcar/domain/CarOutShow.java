package com.example.oldcar.domain;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

/**
 * 说明:车辆外观展示
 *
 * @author WaveLee
 * 日期: 2019/1/19
 */
@Entity
@Table(name = "common_car_out")
public class CarOutShow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("主键id，自增")
    private Long id;

    /**
     * 图1
     */
    @ManyToOne
    private FilePath p1;

    /**
     * 图2
     */
    @ManyToOne
    private FilePath p2;

    /**
     * 图3
     */
    @ManyToOne
    private FilePath p3;

    /**
     * 图4
     */
    @ManyToOne
    private FilePath p4;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FilePath getP1() {
        return p1;
    }

    public void setP1(FilePath p1) {
        this.p1 = p1;
    }

    public FilePath getP2() {
        return p2;
    }

    public void setP2(FilePath p2) {
        this.p2 = p2;
    }

    public FilePath getP3() {
        return p3;
    }

    public void setP3(FilePath p3) {
        this.p3 = p3;
    }

    public FilePath getP4() {
        return p4;
    }

    public void setP4(FilePath p4) {
        this.p4 = p4;
    }
}
