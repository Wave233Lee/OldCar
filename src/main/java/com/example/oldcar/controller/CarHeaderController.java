package com.example.oldcar.controller;

import com.example.oldcar.domain.CarHeader;
import com.example.oldcar.domain.Result;
import com.example.oldcar.service.CarHeaderService;
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
 * 日期: 2018/12/27
 */
@RestController
@RequestMapping(value = "/car")
@Api(tags = "汽车接口")
public class CarHeaderController {
    @Autowired
    private CarHeaderService carHeaderService;

    /**
     * 新增
     */
    @PostMapping(value = "/add")
    public Result<CarHeader> add(@Valid CarHeader informationContent, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
        return ResultUtil.success(carHeaderService.add(informationContent));
    }

    /**
     * 更新
     */
    @PostMapping(value = "/update")
    public Result<CarHeader> update(@Valid CarHeader informationContent){
        return ResultUtil.success(carHeaderService.update(informationContent));
    }

    /**
     * 根据id查询
     */
    @GetMapping(value = "/getById")
    public Result<CarHeader> findById(@RequestParam Long id){
        return ResultUtil.success(carHeaderService.findById(id));
    }

    /**
     * 查询所有
     */
    @GetMapping(value = "/getAll")
    public Result<List<CarHeader>> getAll(){
        return ResultUtil.success(carHeaderService.findAll());
    }

    /**
     * 批量删除
     */
    @DeleteMapping(value = "/deleteByIds")
    public Result<Object> deleteByIds(@RequestParam Long[] ids) {
        carHeaderService.deleteInBatch(ids);
        return ResultUtil.success();
    }

    /**
     * 查询所有-分页
     */
    @GetMapping(value = "/getAllByPage")
    @ApiOperation(value = "查询所有-分页")
    public Result<Page<CarHeader>> getAllByPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                         @RequestParam(value = "size", defaultValue = "10") Integer size,
                                         @RequestParam(value = "sortFieldName", defaultValue = "id") String sortFieldName,
                                         @RequestParam(value = "asc", defaultValue = "1") Integer asc) {

        return ResultUtil.success(carHeaderService.findAllByPage(page, size, sortFieldName, asc));
    }

    /**
     * 通过品牌查询-分页
     */
    @GetMapping(value = "/getByBrand")
    @ApiOperation(value = "通过品牌查询-分页")
    public Result<Page<CarHeader>> getByBrand(@RequestParam(value = "brandId") Long brandId,
                                                @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                @RequestParam(value = "sortFieldName", defaultValue = "id") String sortFieldName,
                                                @RequestParam(value = "asc", defaultValue = "1") Integer asc) {

        return ResultUtil.success(carHeaderService.findByBrandByPage(brandId,page, size, sortFieldName, asc));
    }

    /**
     * 通过级别查询-分页
     */
    @GetMapping(value = "/getByLevel")
    @ApiOperation(value = "通过级别查询-分页")
    public Result<Page<CarHeader>> getByLevel(@RequestParam(value = "level") Integer level,
                                                @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                @RequestParam(value = "sortFieldName", defaultValue = "id") String sortFieldName,
                                                @RequestParam(value = "asc", defaultValue = "1") Integer asc) {

        return ResultUtil.success(carHeaderService.findByLevelByPage(level,page, size, sortFieldName, asc));
    }

    /**
     * 通过价格区间查询-分页
     */
    @GetMapping(value = "/getByPriceBetween")
    @ApiOperation(value = "通过价格区间查询-分页")
    public Result<Page<CarHeader>> getByPriceBetween(@RequestParam(value = "start",defaultValue = "0") Double start,
                                                     @RequestParam(value = "end", defaultValue = "999") Double end,
                                                     @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                     @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                     @RequestParam(value = "sortFieldName", defaultValue = "id") String sortFieldName,
                                                     @RequestParam(value = "asc", defaultValue = "1") Integer asc) {

        return ResultUtil.success(carHeaderService.findByBuyPriceBetweenByPage(start,end,page, size, sortFieldName, asc));
    }
}
