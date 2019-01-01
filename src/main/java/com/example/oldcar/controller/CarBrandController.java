package com.example.oldcar.controller;

import com.example.oldcar.domain.CarBrand;
import com.example.oldcar.domain.Result;
import com.example.oldcar.service.CarBrandService;
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
 * 日期: 2018/12/28
 */
@RestController
@RequestMapping(value = "/carBrand")
@Api(tags = "汽车品牌信息接口")
public class CarBrandController {
    @Autowired
    private CarBrandService carBrandService;

    /**
     * 新增
     */
    @PostMapping(value = "/add")
    public Result<CarBrand> add(@Valid CarBrand informationContent, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
        return ResultUtil.success(carBrandService.add(informationContent));
    }

    /**
     * 更新
     */
    @PostMapping(value = "/update")
    public Result<CarBrand> update(@Valid CarBrand informationContent){
        return ResultUtil.success(carBrandService.update(informationContent));
    }

    /**
     * 根据id查询
     */
    @GetMapping(value = "/getById")
    public Result<CarBrand> findById(@RequestParam Long id){
        return ResultUtil.success(carBrandService.findById(id));
    }

    /**
     * 查询所有
     */
    @GetMapping(value = "/getAll")
    public Result<List<CarBrand>> getAll(){
        return ResultUtil.success(carBrandService.findAll());
    }

    /**
     * 批量删除
     */
    @DeleteMapping(value = "/deleteByIds")
    public Result<Object> deleteByIds(@RequestParam Long[] ids) {
        carBrandService.deleteInBatch(ids);
        return ResultUtil.success();
    }

    /**
     * 查询所有-分页
     */
    @GetMapping(value = "/getAllByPage")
    @ApiOperation(value = "查询所有-分页")
    public Result<Page<CarBrand>> getAllByPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                @RequestParam(value = "sortFieldName", defaultValue = "id") String sortFieldName,
                                                @RequestParam(value = "asc", defaultValue = "1") Integer asc) {

        return ResultUtil.success(carBrandService.findAllByPage(page, size, sortFieldName, asc));
    }
}
