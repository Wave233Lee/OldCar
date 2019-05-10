package com.example.oldcar.controller;

import com.example.oldcar.domain.CarHeader;
import com.example.oldcar.domain.Result;
import com.example.oldcar.domain.User;
import com.example.oldcar.service.CarRecommendService;
import com.example.oldcar.service.UserInterestsService;
import com.example.oldcar.utils.ResultUtil;
import io.swagger.annotations.Api;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/recommend")
@Api(tags = "推荐接口")
public class RecommendController {
    @Autowired
    private UserInterestsService userInterestsService;

    @Autowired
    private CarRecommendService carRecommendService;

    /**
     * 看了又看
     */
    @GetMapping(value = "/carRecommend")
    public Result<List<CarHeader>> CarRecommend(Long id) throws JSONException {
        return ResultUtil.success(carRecommendService.CarRecommend(id));
    }

    /**
     * 初始化brand.txt文件
     */
    @GetMapping(value = "/carBrandTxt")
    public Result<Object> BrandSimilarity() throws IOException {
        carRecommendService.BrandSimilarity();
        return ResultUtil.success();
    }

    /**
     * 初始化level.txt文件
     */
    @GetMapping(value = "/carLevelTxt")
    public Result<Object> LevelSimilarity() throws IOException {
        carRecommendService.LevelSimilarity();
        return ResultUtil.success();
    }

    /**
     * 初始化pricerange.txt文件
     */
    @GetMapping(value = "/priceRangeTxt")
    public Result<Object> PriceRangeSimilarity() throws IOException {
        carRecommendService.PriceRangeSimilarity();
        return ResultUtil.success();
    }

    /**
     * 初始化uselengthrange.txt文件
     */
    @GetMapping(value = "/useLengthRangeTxt")
    public Result<Object> UseLengthRangeSimilarity() throws IOException {
        carRecommendService.UseLengthRangeSimilarity();
        return ResultUtil.success();
    }

    /**
     * 猜你喜欢
     */
    @GetMapping(value = "/userRecommend")
    public Result<List<CarHeader>> UserRecommend() throws JSONException {
        return ResultUtil.success(userInterestsService.UserRecommend());
    }

    /**
     * 初始化InitTable表里的数据
     */
    @GetMapping(value = "/initTable")
    public Result<Object> InitTable() {
        userInterestsService.InitTable();
        return ResultUtil.success();
    }

    /**
     * 初始化init.json文件
     */
    @GetMapping(value = "/initJson")
    public Result<Object> Init() throws IOException, JSONException {
        userInterestsService.Init();
        return ResultUtil.success();
    }

    /**
     * 初始化final.json文件
     */
    @GetMapping(value = "/finalJson")
    public Result<Object> Final(Long id) throws IOException, JSONException {
        userInterestsService.Final(id);
        return ResultUtil.success();
    }


}
