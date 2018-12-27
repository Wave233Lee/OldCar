package com.example.oldcar.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * 说明:
 *
 * @author WaveLee
 * 日期: 2018/12/25
 */
@Entity
@Table(name = "home_page_ads")
public class HomePageAds {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 对应首页
     */
    @ManyToOne
    @JoinColumn(name = "home_page_header",referencedColumnName = "id")
    private HomePageHeader homePageHeader;

    /**
     * 广告图片
     */
    @ManyToOne
    @JoinColumn(name = "ad",referencedColumnName = "id")
    private Ad ad;

    /**
     * 展示顺序
     */
    private Integer sequence;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @JsonIgnore
    public HomePageHeader getHomePageHeader() {
        return homePageHeader;
    }

    public void setHomePageHeader(HomePageHeader homePageHeader) {
        this.homePageHeader = homePageHeader;
    }

    public Ad getAd() {
        return ad;
    }

    public void setAd(Ad ad) {
        this.ad = ad;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }
}
