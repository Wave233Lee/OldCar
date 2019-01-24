package com.example.oldcar.domain;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.List;

/**
 * 说明:车友会
 *
 * @author WaveLee
 * 日期: 2019/1/23
 */
@Entity
@Table(name = "club_header")
public class ClubHeader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("主键id，自增")
    private Long id;

    /**
     * LOGO路径
     */
    @ManyToOne
    @ApiModelProperty("LOGO路径")
    private FilePath picture;

    /**
     * 名称
     */
    @ApiModelProperty("名称")
    private String name;

    /**
     * 简介
     */
    @ApiModelProperty("简介")
    private String introduction;

    /**
     * 标签
     */
    @OneToMany(mappedBy = "club",cascade = CascadeType.ALL)
    @ApiModelProperty("标签")
    private List<ClubTag> tags;

    /**
     * 人数
     */
    @ApiModelProperty("俱乐部人数")
    private Integer number;

    /**
     * 成员
     */
    @OneToMany(mappedBy = "club",cascade = CascadeType.ALL)
    @ApiModelProperty("成员")
    private List<ClubMember> members;

    /**
     * 文章
     */
    @OneToMany(mappedBy = "club",cascade = CascadeType.ALL)
    @ApiModelProperty("文章")
    private List<ClubArticle> articles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FilePath getPicture() {
        return picture;
    }

    public void setPicture(FilePath picture) {
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public List<ClubTag> getTags() {
        return tags;
    }

    public void setTags(List<ClubTag> tags) {
        this.tags = tags;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public List<ClubMember> getMembers() {
        return members;
    }

    public void setMembers(List<ClubMember> members) {
        this.members = members;
    }

    public List<ClubArticle> getArticles() {
        return articles;
    }

    public void setArticles(List<ClubArticle> articles) {
        this.articles = articles;
    }
}
