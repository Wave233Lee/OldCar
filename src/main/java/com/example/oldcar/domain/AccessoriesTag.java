package com.example.oldcar.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

/**
 * 说明:配件标签  进口配件、特价处理等
 *
 * @author WaveLee
 * 日期: 2018/12/28
 */
@Entity
@Table(name = "common_accessories_tag")
public class AccessoriesTag {
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
     * 标签名
     */
    @ApiModelProperty("标签名")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
