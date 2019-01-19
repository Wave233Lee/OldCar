package com.example.oldcar.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * 说明:配件评价
 *
 * @author WaveLee
 * 日期: 2019/1/19
 */
@Entity
@Table(name = "common_accessories_comment")
public class CommentAccessories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("主键id，自增")
    private Long id;

    /**
     * 对应配件
     */
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name = "accessories")
    @ApiModelProperty("对应配件")
    private AccessoriesHeader accessories;

    /**
     * 发表者
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @ApiModelProperty("发表者")
    private User commentor;

    /**
     * 发布时间
     */
    @Temporal(value = TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("发布时间")
    private Date publishTime;

    /**
     * 评论内容
     */
    @ApiModelProperty("评论内容")
    private String note;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AccessoriesHeader getAccessories() {
        return accessories;
    }

    public void setAccessories(AccessoriesHeader accessories) {
        this.accessories = accessories;
    }

    public User getCommentor() {
        return commentor;
    }

    public void setCommentor(User commentor) {
        this.commentor = commentor;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
