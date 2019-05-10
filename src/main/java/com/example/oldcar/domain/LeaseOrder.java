package com.example.oldcar.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * 说明:租赁订单  审核等详细部分待设计
 *
 * @author WaveLee
 * 日期: 2018/12/27
 */
@Entity
@Table(name = "Lease_order_header")
public class LeaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("主键id，自增")
    private Long id;

    /**
     * 租赁人
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @ApiModelProperty("租赁人")
    private User leaser;

    /**
     * 取车地点
     */
    @ApiModelProperty("使用地点：省市区 室内外")
    private String place1;

    /**
     * 还车地点
     */
    @ApiModelProperty("使用地点：省市区 室内外")
    private String place2;

    /**
     * 使用类型 0静展 1巡游
     */
    @ApiModelProperty("使用类型 0静展 1巡游")
    private Integer type;

    /**
     * 数量
     */
    @ApiModelProperty("数量")
    private Integer num;

    /**
     * 起始时间
     */
    @Temporal(value = TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty("起始日期")
    private Date startTime;

    /**
     * 结束时间
     */
    @Temporal(value = TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty("结束时间日期")
    private Date endTime;

    /**
     * 租借车
     */
    @ManyToOne
    private CarHeader car;

    /**
     * 订单标志 0未支付/1已支付/2已取车
     */
    private Integer state;

    /**
     * 开票类型 0单位/1个人
     */
    private Integer kind;

    /**
     * 开票标志 0不需要/1需要
     */
    private Integer need;

    /**
     * 税号
     */
    private String sh;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getLeaser() {
        return leaser;
    }

    public void setLeaser(User leaser) {
        this.leaser = leaser;
    }


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public CarHeader getCar() {
        return car;
    }

    public void setCar(CarHeader car) {
        this.car = car;
    }

    public String getPlace1() {
        return place1;
    }

    public void setPlace1(String place1) {
        this.place1 = place1;
    }

    public String getPlace2() {
        return place2;
    }

    public void setPlace2(String place2) {
        this.place2 = place2;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getKind() {
        return kind;
    }

    public void setKind(Integer kind) {
        this.kind = kind;
    }

    public Integer getNeed() {
        return need;
    }

    public void setNeed(Integer need) {
        this.need = need;
    }

    public String getSh() {
        return sh;
    }

    public void setSh(String sh) {
        this.sh = sh;
    }
}
