package com.example.oldcar.domain;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

/**
 * 说明:关注的人
 *
 * @author WaveLee
 * 日期: 2019/1/19
 */
@Entity
@Table(name = "user_followings")
public class UserFollowing {
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
     * 关注的人
     */
    @ManyToOne
    private User following;

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

    public User getFollowing() {
        return following;
    }

    public void setFollowing(User following) {
        this.following = following;
    }
}
