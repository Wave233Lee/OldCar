package com.example.oldcar.vo;

import com.example.oldcar.domain.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * 说明:文章VO对象
 *
 * @author WaveLee
 * 日期: 2019/1/24
 */
public class ArticleVO {
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 配图
     */
    private FilePath picture;

    /**
     * 类型   0科普1原创
     */
    private Integer type;

    /**
     * 作者
     */
    private User author;

    /**
     * 发布时间
     */
    @Temporal(value = TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date publishTime;

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
    private List<ArticleContent> contents;

    /**
     * 评论内容
     */
    private List<CommentArticle> comment;

    /**
     * PR
     */
    private Integer pr;

    public ArticleVO(ArticleHeader article, Integer pr) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.picture = article.getPicture();
        this.type = article.getType();
        this.author = article.getAuthor();
        this.publishTime = article.getPublishTime();
        this.views = article.getViews();
        this.likes = article.getLikes();
        this.comments = article.getComments();
        this.contents = article.getContents();
        this.comment = article.getComment();
        this.pr = pr;
    }
}
