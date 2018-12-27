package com.example.oldcar.domain;

import javax.persistence.*;

/**
 * 说明:首层评论 位于视频下 次级评论回复结构待设计
 *
 * @author WaveLee
 * 日期: 2018/12/25
 */
@Entity
@Table(name = "common_comment_video")
public class CommentVideo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 对应视频
     */
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name = "video")
    private VideoHeader video;

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

    public VideoHeader getVideo() {
        return video;
    }

    public void setVideo(VideoHeader video) {
        this.video = video;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
