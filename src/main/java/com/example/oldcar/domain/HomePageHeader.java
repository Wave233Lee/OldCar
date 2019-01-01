package com.example.oldcar.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.List;

/**
 * 说明:
 *
 * @author WaveLee
 * 日期: 2018/12/21
 */
@Entity
@Table(name = "home_page_header")
@ApiModel(description = "首页表头")
public class HomePageHeader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("主键id，自增")
    private Long id;

    /**
     * 广告图片组
     */
    @OneToMany(targetEntity = HomePageAds.class)
    @JoinColumn(name = "home_page_header",referencedColumnName = "id")
    @ApiModelProperty("广告图片组")
    private List<HomePageAds> ads;

    /**
     * 头条
     */
    @OneToMany(targetEntity = HomePageHeadline.class)
    @JoinColumn(name = "home_page_header",referencedColumnName = "id")
    @ApiModelProperty("头条")
    private List<HomePageHeadline> headlines;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<HomePageAds> getAds() {
        return ads;
    }

    public void setAds(List<HomePageAds> ads) {
        this.ads = ads;
    }

    public List<HomePageHeadline> getHeadlines() {
        return headlines;
    }

    public void setHeadlines(List<HomePageHeadline> headlines) {
        this.headlines = headlines;
    }
}
