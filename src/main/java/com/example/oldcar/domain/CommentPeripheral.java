package com.example.oldcar.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * 说明:周边产品评论
 *
 * @author WaveLee
 * 日期: 2019/1/23
 */
@Entity
@Table(name = "common_peripheral_comment")
public class CommentPeripheral {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("主键id，自增")
    private Long id;

    /**
     * 对应产品
     */
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name = "peripheral")
    @ApiModelProperty("对应产品")
    private PeripheralHeader peripheral;

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

    public PeripheralHeader getPeripheral() {
        return peripheral;
    }

    public void setPeripheral(PeripheralHeader peripheral) {
        this.peripheral = peripheral;
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
