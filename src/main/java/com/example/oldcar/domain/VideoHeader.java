package com.example.oldcar.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * 说明:视频 通用视频类
 *
 * @author WaveLee
 * 日期: 2018/12/25
 */
@Entity
@Table(name = "common_video")
public class VideoHeader {
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
     * 类型   0视频1活动展示
     */
    @ApiModelProperty("类型   0视频1活动展示")
    private Integer type;

    /**
     * 视频路径
     */
    @ManyToOne
    @ApiModelProperty("视频路径")
    private FilePath filePath;

    /**
     * 发布者
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @ApiModelProperty("发布者")
    private User publisher;

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
     * 评论内容
     */
    @OneToMany(mappedBy = "video",cascade = CascadeType.ALL)
    @ApiModelProperty("评论内容")
    private List<CommentVideo> comment;

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public FilePath getFilePath() {
        return filePath;
    }

    public void setFilePath(FilePath filePath) {
        this.filePath = filePath;
    }

    public User getPublisher() {
        return publisher;
    }

    public void setPublisher(User publisher) {
        this.publisher = publisher;
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

    public List<CommentVideo> getComment() {
        return comment;
    }

    public void setComment(List<CommentVideo> comment) {
        this.comment = comment;
    }
}
