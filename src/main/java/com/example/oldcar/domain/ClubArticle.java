package com.example.oldcar.domain;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

/**
 * 说明:车友会文章
 *
 * @author WaveLee
 * 日期: 2019/1/23
 */
@Entity
@Table(name = "club_article")
public class ClubArticle {
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
     * 文章
     */
    @ManyToOne
    private ArticleHeader article;

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

    public ArticleHeader getArticle() {
        return article;
    }

    public void setArticle(ArticleHeader article) {
        this.article = article;
    }
}
