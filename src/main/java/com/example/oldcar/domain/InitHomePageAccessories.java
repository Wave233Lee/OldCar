package com.example.oldcar.domain;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

/**
 * 说明:首页—老车配件页面初始设定，含广告图，用于管理端设定
 *
 * @author WaveLee
 * 日期: 2018/12/28
 */
@Entity
@Table(name = "init_homepage_accessories")
public class InitHomePageAccessories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("主键id，自增")
    private Long id;

    /**
     * 广告图片
     */
    @ManyToOne
    @JoinColumn(name = "ad",referencedColumnName = "id")
    @ApiModelProperty("广告图片")
    private Ad ad;

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

    public Ad getAd() {
        return ad;
    }

    public void setAd(Ad ad) {
        this.ad = ad;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
