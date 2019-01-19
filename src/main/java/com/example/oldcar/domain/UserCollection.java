package com.example.oldcar.domain;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

/**
 * 说明:用户收藏的东西
 *
 * @author WaveLee
 * 日期: 2019/1/19
 */
@Entity
@Table(name = "user_collection")
public class UserCollection {
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
     * 收藏的类型 0文章/1视频/2车/3配件
     */
    private Integer type;

    /**
     * 数据id
     */
    private Long collectId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getCollectId() {
        return collectId;
    }

    public void setCollectId(Long collectId) {
        this.collectId = collectId;
    }
}
