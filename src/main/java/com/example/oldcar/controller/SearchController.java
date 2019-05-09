package com.example.oldcar.controller;

import com.example.oldcar.domain.AccessoriesHeader;
import com.example.oldcar.domain.CarHeader;
import com.example.oldcar.domain.Result;
import com.example.oldcar.service.SearchService;
import com.example.oldcar.utils.ResultUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 说明:搜索接口
 *
 * @author WaveLee
 * 日期: 2019/5/6
 */
@RestController
@RequestMapping(value = "/search")
@Api(tags = "搜索接口")
public class SearchController {
    @Autowired
    private SearchService searchService;

    /**
     * 根据关键词查询老爷车
     */
    @GetMapping(value = "/getOldCar")
    public Result<List<CarHeader>> findOldCar(@RequestParam String keyWord){
        return ResultUtil.success(searchService.searchCar0(keyWord));
    }

    /**
     * 根据关键词查询进口车
     */
    @GetMapping(value = "/getImportCar")
    public Result<List<CarHeader>> findImportCar(@RequestParam String keyWord){
        return ResultUtil.success(searchService.searchCar1(keyWord));
    }

    /**
     * 根据关键词查询二手车
     */
    @GetMapping(value = "/getSecondCar")
    public Result<List<CarHeader>> findSecondCar(@RequestParam String keyWord){
        return ResultUtil.success(searchService.searchCar2(keyWord));
    }

    /**
     * 根据关键词查询新能源车
     */
    @GetMapping(value = "/getNewCar")
    public Result<List<CarHeader>> findNewCar(@RequestParam String keyWord){
        return ResultUtil.success(searchService.searchCar3(keyWord));
    }
    /**
     * 根据关键词查询配件
     */
    @GetMapping(value = "/getImportCar")
    public Result<List<AccessoriesHeader>> findAccessories(@RequestParam String keyWord){
        return ResultUtil.success(searchService.searchAccessories(keyWord));
    }
}
