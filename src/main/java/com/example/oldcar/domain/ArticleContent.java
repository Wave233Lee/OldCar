package com.example.oldcar.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
    private Long id;

    /**
     * 对应文章
     */
    @ManyToOne
    @JoinColumn(name = "article_content",referencedColumnName = "id")
    private ArticleHeader article;

    /**
     * 图文组
     */
    @ManyToOne
    @JoinColumn(name = "content_group",referencedColumnName = "id")
    private ContentGroup contentGroup;

    /**
     * 顺序
     */
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
