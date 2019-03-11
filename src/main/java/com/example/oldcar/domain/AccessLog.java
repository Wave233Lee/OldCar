package com.example.oldcar.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * 说明:用户访问接口日志
 *
 * @author WaveLee
 * 日期: 2019/3/6
 */
@Entity
@Table(name = "access_log")
@ApiModel(description = "用户接口访问日志")
public class AccessLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("主键id，自增")
    private Long id;

    /**
     * 访问url
     */
    private String url;

    /**
     * 请求方式
     */
    private String method;

    /**
     * 用户ip
     */
    private String ip;

    /**
     * 访问用户
     */
    @ManyToOne
    private User user;

    /**
     * 访问时间
     */
    @Temporal(value = TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;

    /**
     * 类方法
     */
    private String classMethod;

    /**
     * 参数
     */
    private String args;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getClassMethod() {
        return classMethod;
    }

    public void setClassMethod(String classMethod) {
        this.classMethod = classMethod;
    }

    public String getArgs() {
        return args;
    }

    public void setArgs(String args) {
        this.args = args;
    }
}
