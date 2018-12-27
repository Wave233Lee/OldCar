package com.example.oldcar.controller;

import com.example.oldcar.domain.HomePageHeader;
import com.example.oldcar.domain.Result;
import com.example.oldcar.service.HomePageHeaderService;
import com.example.oldcar.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

/**
 * 说明:主页信息
 *
 * @author WaveLee
 * 日期: 2018/12/22
 */
@RestController
@RequestMapping(value = "/homePage")
@Api(tags = "主页信息接口")
public class HomePageHeaderController {
    @Autowired
    private HomePageHeaderService homePageHeaderService;

    /**
     * 新增
     */
    @PostMapping(value = "/add")
    public Result<HomePageHeader> add(@Valid HomePageHeader informationContent, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
        return ResultUtil.success(homePageHeaderService.add(informationContent));
    }

    /**
     * 更新
     */
    @PostMapping(value = "/update")
    public Result<HomePageHeader> update(@Valid HomePageHeader informationContent){
        return ResultUtil.success(homePageHeaderService.update(informationContent));
    }

    /**
     * 根据id查询
     */
    @GetMapping(value = "/getById")
    public Result<HomePageHeader> findById(@RequestParam Long id){
        return ResultUtil.success(homePageHeaderService.findById(id));
    }

    /**
     * 查询所有
     */
    @GetMapping(value = "/getAll")
    public Result<List<HomePageHeader>> getAll(){
        return ResultUtil.success(homePageHeaderService.findAll());
    }

    /**
     * 批量删除
     */
    @DeleteMapping(value = "/deleteByIds")
    public Result<Object> deleteByIds(@RequestParam Long[] ids) {
        homePageHeaderService.deleteInBatch(ids);
        return ResultUtil.success();
    }

    /**
     * 查询所有-分页
     */
    @GetMapping(value = "/getAllByPage")
    @ApiOperation(value = "查询所有-分页")
    public Result<Page<HomePageHeader>> getAllByPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                           @RequestParam(value = "size", defaultValue = "10") Integer size,
                                           @RequestParam(value = "sortFieldName", defaultValue = "id") String sortFieldName,
                                           @RequestParam(value = "asc", defaultValue = "1") Integer asc) {

        return ResultUtil.success(homePageHeaderService.findAllByPage(page, size, sortFieldName, asc));
    }
}
