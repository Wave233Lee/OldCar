package com.example.oldcar.domain;

import javax.persistence.*;
import java.util.List;

/**
 * 说明:文章表头
 *
 * @author WaveLee
 * 日期: 2018/12/22
 */
@Entity
@Table(name = "common_article_header")
public class ArticleHeader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 配图
     */
    @ManyToOne
    private FilePath picture;

    /**
     * 类型   0科普1原创
     */
    private Integer type;

    /**
     * 作者
     */
    @ManyToOne(fetch = FetchType.LAZY)
    private User author;

    /**
     * 阅读数
     */
    private Long views;

    /**
     * 点赞数
     */
    private Long likes;

    /**
     * 评论数
     */
    private Long comments;

    /**
     * 文章内容
     */
    @OneToMany
    @JoinColumn(name = "article_content",referencedColumnName = "id")
    private List<ArticleContent> contents;

    /**
     * 评论内容
     */
    @OneToMany(mappedBy = "article",cascade = CascadeType.ALL)
    private List<CommentArticle> comment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public FilePath getPicture() {
        return picture;
    }

    public void setPicture(FilePath picture) {
        this.picture = picture;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Long getViews() {
        return views;
    }

    public void setViews(Long views) {
        this.views = views;
    }

    public Long getLikes() {
        return likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }

    public Long getComments() {
        return comments;
    }

    public void setComments(Long comments) {
        this.comments = comments;
    }

    public List<ArticleContent> getContents() {
        return contents;
    }

    public void setContents(List<ArticleContent> contents) {
        this.contents = contents;
    }

    public List<CommentArticle> getComment() {
        return comment;
    }

    public void setComment(List<CommentArticle> comment) {
        this.comment = comment;
    }
}
