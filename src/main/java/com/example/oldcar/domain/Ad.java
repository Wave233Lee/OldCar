package com.example.oldcar.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

/**
 * 说明:广告图片  适用于：首页广告/租赁广告
 *
 * @author WaveLee
 * 日期: 2018/12/21
 */
@Entity
@Table(name = "common_ad")
@ApiModel(description = "广告图片")
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("主键id，自增")
    private Long id;

    /**
     * 图片地址
     */
    @ManyToOne
    @ApiModelProperty("图片路径")
    private FilePath filePath;

    /**
     * 投放位置（可为空）
     */
    @ApiModelProperty("投放位置（可为空）")
    private String location;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String note;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FilePath getFilePath() {
        return filePath;
    }

    public void setFilePath(FilePath filePath) {
        this.filePath = filePath;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
