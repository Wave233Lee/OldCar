package com.example.oldcar.domain;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

/**
 * 说明:配件种类  灯光、轮毂、内饰、刹车系统、动力系统、悬挂系统等
 *
 * @author WaveLee
 * 日期: 2018/12/28
 */
@Entity
@Table(name = "common_accessories_type")
public class AccessoriesType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("主键id，自增")
    private Long id;

    /**
     * 种类名
     */
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
