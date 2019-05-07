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
    public Result<List<CarHeader>> CarRecommend(@RequestParam CarHeader carHeader) throws JSONException {
        return ResultUtil.success(carRecommendService.CarRecommend(carHeader));
    }

    /**
     * 猜你喜欢
     */
    @GetMapping(value = "/userRecommend")
    public Result<List<CarHeader>> UserRecommend(@RequestParam User user) throws JSONException {
        return ResultUtil.success(userInterestsService.UserRecommend(user));
    }


}
