package com.example.oldcar.domain;

import javax.persistence.*;

/**
 * 说明:首层评论 位于文章下 次级评论回复结构待设计
 *
 * @author WaveLee
 * 日期: 2018/12/25
 */
@Entity
@Table(name = "common_article_comment")
public class CommentArticle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 对应文章
     */
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name = "article")
    private ArticleHeader article;

    /**
     * 发表者
     */
    @ManyToOne(fetch = FetchType.LAZY)
    private User commentor;

    /**
     * 评论内容
     */
    private String note;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ArticleHeader getArticle() {
        return article;
    }

    public void setArticle(ArticleHeader article) {
        this.article = article;
    }

    public User getCommentor() {
        return commentor;
    }

    public void setCommentor(User commentor) {
        this.commentor = commentor;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
