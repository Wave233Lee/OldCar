package com.example.oldcar.domain;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.List;

/**
 * 说明:金融服务商
 *
 * @author WaveLee
 * 日期: 2019/1/23
 */
@Entity
@Table(name = "financial_service_provider")
public class Financial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("主键id，自增")
    private Long id;

    /**
     * 公司LOGO路径
     */
    @ManyToOne
    @ApiModelProperty("LOGO路径")
    private FilePath picture;

    /**
     * 公司名称
     */
    @ApiModelProperty("公司名称")
    private String name;

    /**
     * 类目
     */
    @ManyToOne
    @ApiModelProperty("类目")
    private FinancialType type;

    /**
     * 类型
     */
    @ManyToOne
    @ApiModelProperty("服务类型")
    private FinancialKind kind;

    /**
     * 服务地区
     */
    @ApiModelProperty("服务地区")
    @OneToMany(mappedBy = "financial",cascade = CascadeType.ALL)
    private List<FinancialLocation> locations;

    /**
     * 价格
     */
    @ApiModelProperty("价格")
    private Integer price;

    /**
     * 优惠
     */
    @ApiModelProperty("优惠")
    private String discount;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FinancialType getType() {
        return type;
    }

    public void setType(FinancialType type) {
        this.type = type;
    }

    public FinancialKind getKind() {
        return kind;
    }

    public void setKind(FinancialKind kind) {
        this.kind = kind;
    }

    public List<FinancialLocation> getLocations() {
        return locations;
    }

    public void setLocations(List<FinancialLocation> locations) {
        this.locations = locations;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }
}
