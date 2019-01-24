package com.example.oldcar.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

/**
 * 说明:周边产品标签
 *
 * @author WaveLee
 * 日期: 2019/1/23
 */
@Entity
@Table(name = "peripheral_tag")
public class PeripheralTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("主键id，自增")
    private Long id;

    /**
     * 对应周边
     */
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name = "peripheral")
    @ApiModelProperty("对应周边")
    private PeripheralHeader peripheral;

    /**
     * 标签
     */
    @ApiModelProperty("标签")
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

    @JsonIgnore
    public PeripheralHeader getPeripheral() {
        return peripheral;
    }

    public void setPeripheral(PeripheralHeader peripheral) {
        this.peripheral = peripheral;
    }
}
