package com.example.oldcar.domain;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.List;

/**
 * 说明:周边产品
 *
 * @author WaveLee
 * 日期: 2019/1/23
 */
@Entity
@Table(name = "peripheral_products_header")
public class PeripheralHeader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("主键id，自增")
    private Long id;

    /**
     * 种类
     */
    @ManyToOne
    @ApiModelProperty("种类")
    private PeripheralType type;

    /**
     * 产品图
     */
    @ManyToOne
    @ApiModelProperty("产品图")
    private FilePath picture;

    /**
     * 品牌
     */
    @ApiModelProperty("品牌")
    private String brand;

    /**
     * 产品名称
     */
    private String name;

    /**
     * 型号
     */
    @ApiModelProperty("型号")
    private String model;

    /**
     * 规格
     */
    @ApiModelProperty("规格")
    private String size;

    /**
     * 颜色
     */
    @ApiModelProperty("颜色")
    private String color;

    /**
     * 供应商
     */
    @ApiModelProperty("供应商")
    @ManyToOne
    private Supplier supplier;

    /**
     * 图文描述
     */
    @ApiModelProperty("图文描述")
    @ManyToOne
    private ContentGroup contentGroup;

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
    @OneToMany(mappedBy = "peripheral",cascade = CascadeType.ALL)
    @ApiModelProperty("标签")
    private List<PeripheralTag> tag;

    /**
     * 库存
     */
    @ApiModelProperty("库存")
    private Integer num;

    /**
     * 评价
     */
    @OneToMany(mappedBy = "peripheral",cascade = CascadeType.ALL)
    @ApiModelProperty("评价")
    private List<CommentPeripheral> comment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PeripheralType getType() {
        return type;
    }

    public void setType(PeripheralType type) {
        this.type = type;
    }

    public FilePath getPicture() {
        return picture;
    }

    public void setPicture(FilePath picture) {
        this.picture = picture;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public ContentGroup getContentGroup() {
        return contentGroup;
    }

    public void setContentGroup(ContentGroup contentGroup) {
        this.contentGroup = contentGroup;
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

    public List<PeripheralTag> getTag() {
        return tag;
    }

    public void setTag(List<PeripheralTag> tag) {
        this.tag = tag;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public List<CommentPeripheral> getComment() {
        return comment;
    }

    public void setComment(List<CommentPeripheral> comment) {
        this.comment = comment;
    }
}
