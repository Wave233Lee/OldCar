package com.example.oldcar.controller;

import com.example.oldcar.domain.Ad;
import com.example.oldcar.domain.Result;
import com.example.oldcar.service.AdService;
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
 * 说明:
 *
 * @author WaveLee
 * 日期: 2018/12/25
 */
@RestController
@RequestMapping(value = "/ad")
@Api(tags = "广告接口")
public class AdController {
    @Autowired
    private AdService adService;

    /**
     * 新增
     */
    @PostMapping(value = "/add")
    public Result<Ad> add(@Valid Ad informationContent, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
        return ResultUtil.success(adService.add(informationContent));
    }

    /**
     * 更新
     */
    @PostMapping(value = "/update")
    public Result<Ad> update(@Valid Ad informationContent){
        return ResultUtil.success(adService.update(informationContent));
    }

    /**
     * 根据id查询
     */
    @GetMapping(value = "/getById")
    public Result<Ad> findById(@RequestParam Long id){
        return ResultUtil.success(adService.findById(id));
    }

    /**
     * 查询所有
     */
    @GetMapping(value = "/getAll")
    public Result<List<Ad>> getAll(){
        return ResultUtil.success(adService.findAll());
    }

    /**
     * 批量删除
     */
    @DeleteMapping(value = "/deleteByIds")
    public Result<Object> deleteByIds(@RequestParam Long[] ids) {
        adService.deleteInBatch(ids);
        return ResultUtil.success();
    }

    /**
     * 查询所有-分页
     */
    @GetMapping(value = "/getAllByPage")
    @ApiOperation(value = "查询所有-分页")
    public Result<Page<Ad>> getAllByPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                           @RequestParam(value = "size", defaultValue = "10") Integer size,
                                           @RequestParam(value = "sortFieldName", defaultValue = "id") String sortFieldName,
                                           @RequestParam(value = "asc", defaultValue = "1") Integer asc) {

        return ResultUtil.success(adService.findAllByPage(page, size, sortFieldName, asc));
    }

    /**
     * 根据投放位置查询
     */
    @GetMapping(value = "/getByLocation")
    @ApiOperation(value = "根据投放位置查询")
    public Result<Ad> findByLocation(@RequestParam String location){
        return ResultUtil.success(adService.findByLocation(location));
    }

}
