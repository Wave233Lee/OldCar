package com.example.oldcar.domain;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

/**
 * 说明:车友会成员
 *
 * @author WaveLee
 * 日期: 2019/1/23
 */
@Entity
@Table(name = "club_member")
public class ClubMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("主键id，自增")
    private Long id;

    /**
     * 对应车友会
     */
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name = "club")
    @ApiModelProperty("对应车友会")
    private ClubHeader club;

    /**
     * 成员
     */
    @ManyToOne
    private User member;

    /**
     * 成员等级
     */
    private Integer level;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClubHeader getClub() {
        return club;
    }

    public void setClub(ClubHeader club) {
        this.club = club;
    }

    public User getMember() {
        return member;
    }

    public void setMember(User member) {
        this.member = member;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
