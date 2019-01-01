package com.example.oldcar.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

/**
 * 说明:文章内容 一图一文为一组
 *
 * @author WaveLee
 * 日期: 2018/12/22
 */
@Entity
@Table(name = "common_article_content")
public class ArticleContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("主键id，自增")
    private Long id;

    /**
     * 对应文章
     */
    @ManyToOne
    @JoinColumn(name = "article_content",referencedColumnName = "id")
    @ApiModelProperty("对应文章")
    private ArticleHeader article;

    /**
     * 图文组
     */
    @ManyToOne
    @JoinColumn(name = "content_group",referencedColumnName = "id")
    @ApiModelProperty("图文组")
    private ContentGroup contentGroup;

    /**
     * 顺序
     */
    @ApiModelProperty("顺序")
    private Integer sequence;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }
    @JsonIgnore

    public ArticleHeader getArticle() {
        return article;
    }

    public void setArticle(ArticleHeader article) {
        this.article = article;
    }

    public ContentGroup getContentGroup() {
        return contentGroup;
    }

    public void setContentGroup(ContentGroup contentGroup) {
        this.contentGroup = contentGroup;
    }
}
