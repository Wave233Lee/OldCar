package com.example.oldcar.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
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
    @ApiModelProperty("主键id，自增")
    private Long id;

    /**
     * 标题
     */
    @ApiModelProperty("标题")
    private String title;

    /**
     * 配图
     */
    @ManyToOne
    @ApiModelProperty("配图")
    private FilePath picture;

    /**
     * 类型   0科普1原创
     */
    @ApiModelProperty("类型，0科普 1原创")
    private Integer type;

    /**
     * 作者
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @ApiModelProperty("作者")
    private User author;

    /**
     * 发布时间
     */
    @Temporal(value = TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("发布时间")
    private Date publishTime;

    /**
     * 阅读数
     */
    @ApiModelProperty("阅读数")
    private Long views;

    /**
     * 点赞数
     */
    @ApiModelProperty("点赞数")
    private Long likes;

    /**
     * 评论数
     */
    @ApiModelProperty("评论数")
    private Long comments;

    /**
     * 文章内容
     */
    @OneToMany
    @JoinColumn(name = "article_content",referencedColumnName = "id")
    @ApiModelProperty("文章内容")
    private List<ArticleContent> contents;

    /**
     * 评论内容
     */
    @OneToMany(mappedBy = "article",cascade = CascadeType.ALL)
    @ApiModelProperty("评论内容")
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

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
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
