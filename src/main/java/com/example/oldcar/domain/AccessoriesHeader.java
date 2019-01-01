package com.example.oldcar.domain;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.List;

/**
 * 说明:配件表头
 *
 * @author WaveLee
 * 日期: 2018/12/28
 */
@Entity
@Table(name = "common_accessories_header")
public class AccessoriesHeader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("主键id，自增")
    private Long id;

    /**
     * 种类
     */
    @ManyToOne
    @ApiModelProperty("种类")
    private AccessoriesType type;

    /**
     * 产品图
     */
    @ManyToOne
    @ApiModelProperty("车型图")
    private FilePath picture;

    /**
     * 价格
     */
    @ApiModelProperty("价格")
    private Double price;

    /**
     * 活动价格
     */
    @ApiModelProperty("活动价格")
    private Double priceSale;

    /**
     * 标签   进口配件、特价处理等
     */
    @OneToMany(mappedBy = "accessories",cascade = CascadeType.ALL)
    @ApiModelProperty("标签")
    private List<AccessoriesTag> tag;

    /**
     * 库存
     */
    @ApiModelProperty("库存")
    private Integer num;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AccessoriesType getType() {
        return type;
    }

    public void setType(AccessoriesType type) {
        this.type = type;
    }

    public FilePath getPicture() {
        return picture;
    }

    public void setPicture(FilePath picture) {
        this.picture = picture;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPriceSale() {
        return priceSale;
    }

    public void setPriceSale(Double priceSale) {
        this.priceSale = priceSale;
    }

    public List<AccessoriesTag> getTag() {
        return tag;
    }

    public void setTag(List<AccessoriesTag> tag) {
        this.tag = tag;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
