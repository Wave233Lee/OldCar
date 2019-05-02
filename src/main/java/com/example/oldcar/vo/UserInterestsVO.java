package com.example.oldcar.vo;

import com.example.oldcar.domain.CarBrand;
import com.example.oldcar.domain.CarSeries;
import com.example.oldcar.domain.User;

import java.util.Map;

public class UserInterestsVO {
    //对应用户
    private User user;

    //品牌评分
    private Map<CarBrand,Double> brandV;

    //车系评分
    private Map<CarSeries,Double> seriesV;

    //价格区间评分
    private Map<Integer,Double> priceRangeV;

    //车级评分
    private Map<Integer,Double> levelV;

    //使用时长评分
    private Map<Integer,Double> useLengthV;

    public UserInterestsVO(User user, Map<CarBrand, Double> brandV, Map<CarSeries, Double> seriesV, Map<Integer, Double> priceRangeV, Map<Integer, Double> levelV, Map<Integer, Double> useLengthV) {
        this.user = user;
        this.brandV = brandV;
        this.seriesV = seriesV;
        this.priceRangeV = priceRangeV;
        this.levelV = levelV;
        this.useLengthV = useLengthV;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Map<CarBrand, Double> getBrandV() {
        return brandV;
    }

    public void setBrandV(Map<CarBrand, Double> brandV) {
        this.brandV = brandV;
    }

    public Map<CarSeries, Double> getSeriesV() {
        return seriesV;
    }

    public void setSeriesV(Map<CarSeries, Double> seriesV) {
        this.seriesV = seriesV;
    }

    public Map<Integer, Double> getPriceRangeV() {
        return priceRangeV;
    }

    public void setPriceRangeV(Map<Integer, Double> priceRangeV) {
        this.priceRangeV = priceRangeV;
    }

    public Map<Integer, Double> getLevelV() {
        return levelV;
    }

    public void setLevelV(Map<Integer, Double> levelV) {
        this.levelV = levelV;
    }

    public Map<Integer, Double> getUseLengthV() {
        return useLengthV;
    }

    public void setUseLengthV(Map<Integer, Double> useLengthV) {
        this.useLengthV = useLengthV;
    }
}
