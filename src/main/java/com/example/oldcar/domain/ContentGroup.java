package com.example.oldcar.domain;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

/**
 * 说明:图文信息组
 *
 * @author WaveLee
 * 日期: 2018/12/22
 */
@Entity
@Table(name = "common_content_group")
public class ContentGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("主键id，自增")
    private Long id;

    /**
     * 图片
     */
    @ManyToOne
    @ApiModelProperty("图片")
    private FilePath picture;

    /**
     * 文字
     */
    @ApiModelProperty("文字")
    private String note;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FilePath getPicture() {
        return picture;
    }

    public void setPicture(FilePath picture) {
        this.picture = picture;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
