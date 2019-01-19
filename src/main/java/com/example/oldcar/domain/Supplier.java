package com.example.oldcar.domain;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

/**
 * 说明:供应商/4S店 信息
 *
 * @author WaveLee
 * 日期: 2019/1/19
 */
@Entity
@Table(name = "common_supplier")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("主键id，自增")
    private Long id;

    /**
     * 商家名称
     */
    @ApiModelProperty("商家名称")
    private String name;

    /**
     * 公司位置
     */
    @ApiModelProperty("公司位置")
    private String location;

    /**
     * 联系电话
     */
    private String tel;

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
}
