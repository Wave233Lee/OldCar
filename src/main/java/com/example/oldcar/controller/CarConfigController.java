package com.example.oldcar.controller;

import com.example.oldcar.domain.CarConfig;
import com.example.oldcar.domain.Result;
import com.example.oldcar.service.CarConfigService;
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
@RequestMapping(value = "/carConfig")
@Api(tags = "汽车配置信息接口")
public class CarConfigController {
    @Autowired
    private CarConfigService carConfigService;

    /**
     * 新增
     */
    @PostMapping(value = "/add")
    public Result<CarConfig> add(@Valid CarConfig informationContent, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
        return ResultUtil.success(carConfigService.add(informationContent));
    }

    /**
     * 更新
     */
    @PostMapping(value = "/update")
    public Result<CarConfig> update(@Valid CarConfig informationContent){
        return ResultUtil.success(carConfigService.update(informationContent));
    }

    /**
     * 根据id查询
     */
    @GetMapping(value = "/getById")
    public Result<CarConfig> findById(@RequestParam Long id){
        return ResultUtil.success(carConfigService.findById(id));
    }

    /**
     * 查询所有
     */
    @GetMapping(value = "/getAll")
    public Result<List<CarConfig>> getAll(){
        return ResultUtil.success(carConfigService.findAll());
    }

    /**
     * 批量删除
     */
    @DeleteMapping(value = "/deleteByIds")
    public Result<Object> deleteByIds(@RequestParam Long[] ids) {
        carConfigService.deleteInBatch(ids);
        return ResultUtil.success();
    }

    /**
     * 查询所有-分页
     */
    @GetMapping(value = "/getAllByPage")
    @ApiOperation(value = "查询所有-分页")
    public Result<Page<CarConfig>> getAllByPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                @RequestParam(value = "sortFieldName", defaultValue = "id") String sortFieldName,
                                                @RequestParam(value = "asc", defaultValue = "1") Integer asc) {

        return ResultUtil.success(carConfigService.findAllByPage(page, size, sortFieldName, asc));
    }
}
