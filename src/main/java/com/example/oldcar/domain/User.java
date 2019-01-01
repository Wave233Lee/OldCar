package com.example.oldcar.domain;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

/**
 * 说明:
 *
 * @author WaveLee
 * 日期: 2018/12/12
 */
@Entity
public class User {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("主键id，自增")
    private Long id;

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String userName;

    /**
     * 用户头像
     */
    @ManyToOne(targetEntity = FilePath.class)
    @JoinColumn(name = "avatar_id",referencedColumnName = "id")
    @ApiModelProperty("用户头像")
    private FilePath avatar;

    /**
     * 等级
     */
    @ApiModelProperty("等级")
    private Integer level;

    /**
     * 关注
     */
    @ApiModelProperty("关注数")
    private Integer follow;

    /**
     * 收藏
     */
    @ApiModelProperty("收藏数")
    private Integer collection;

    /**
     * 足迹
     */
    @ApiModelProperty("足迹数")
    private Integer history;

    /**
     * 积分
     */
    @ApiModelProperty("积分数")
    private Integer integration;

    /**
     * 获赞
     */
    @ApiModelProperty("获赞数")
    private Integer praise;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public FilePath getAvatar() {
        return avatar;
    }

    public void setAvatar(FilePath avatar) {
        this.avatar = avatar;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getFollow() {
        return follow;
    }

    public void setFollow(Integer follow) {
        this.follow = follow;
    }

    public Integer getCollection() {
        return collection;
    }

    public void setCollection(Integer collection) {
        this.collection = collection;
    }

    public Integer getHistory() {
        return history;
    }

    public void setHistory(Integer history) {
        this.history = history;
    }

    public Integer getIntegration() {
        return integration;
    }

    public void setIntegration(Integer integration) {
        this.integration = integration;
    }

    public Integer getPraise() {
        return praise;
    }

    public void setPraise(Integer praise) {
        this.praise = praise;
    }
}
