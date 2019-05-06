package com.example.oldcar.domain;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

/**
 * 说明:品牌    含LOGO和名称
 *
 * @author WaveLee
 * 日期: 2018/12/25
 */
@Entity
@Table(name = "common_car_brand")
public class CarBrand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("主键id，自增")
    private Long id;

    /**
     * LOGO路径
     */
    @ApiModelProperty("LOGO路径")
    private String brandImg;

    /**
     * 名称
     */
    @ApiModelProperty("名称")
    private String brandName;

    /**
     * 品牌热门值
     */
    private Integer hot;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String note;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrandImg() {
        return brandImg;
    }

    public void setBrandImg(String brandImg) {
        this.brandImg = brandImg;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Integer getHot() {
        return hot;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
