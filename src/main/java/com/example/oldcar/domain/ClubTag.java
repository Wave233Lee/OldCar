package com.example.oldcar.domain;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

/**
 * 说明:车友会标签
 *
 * @author WaveLee
 * 日期: 2019/1/23
 */
@Entity
@Table(name = "club_tag")
public class ClubTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("主键id，自增")
    private Long id;

    /**
     * 标签
     */
    private String name;

    /**
     * 对应车友会
     */
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name = "club")
    @ApiModelProperty("对应车友会")
    private ClubHeader club;


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

    public ClubHeader getClub() {
        return club;
    }

    public void setClub(ClubHeader club) {
        this.club = club;
    }
}
