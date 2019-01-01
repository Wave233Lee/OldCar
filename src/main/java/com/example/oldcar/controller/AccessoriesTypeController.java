package com.example.oldcar.controller;

import com.example.oldcar.domain.AccessoriesType;
import com.example.oldcar.domain.Result;
import com.example.oldcar.service.AccessoriesTypeService;
import com.example.oldcar.utils.ResultUtil;
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
@RequestMapping(value = "/accessoriesType")
public class AccessoriesTypeController {
    @Autowired
    private AccessoriesTypeService accessoriesTypeService;

    /**
     * 新增
     */
    @PostMapping(value = "/add")
    public Result<AccessoriesType> add(@Valid AccessoriesType informationContent, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
        return ResultUtil.success(accessoriesTypeService.add(informationContent));
    }

    /**
     * 更新
     */
    @PostMapping(value = "/update")
    public Result<AccessoriesType> update(@Valid AccessoriesType informationContent){
        return ResultUtil.success(accessoriesTypeService.update(informationContent));
    }

    /**
     * 根据id查询
     */
    @GetMapping(value = "/getById")
    public Result<AccessoriesType> findById(@RequestParam Long id){
        return ResultUtil.success(accessoriesTypeService.findById(id));
    }

    /**
     * 查询所有
     */
    @GetMapping(value = "/getAll")
    public Result<List<AccessoriesType>> getAll(){
        return ResultUtil.success(accessoriesTypeService.findAll());
    }

    /**
     * 批量删除
     */
    @DeleteMapping(value = "/deleteByIds")
    public Result<Object> deleteByIds(@RequestParam Long[] ids) {
        accessoriesTypeService.deleteInBatch(ids);
        return ResultUtil.success();
    }

    /**
     * 查询所有-分页
     */
    @GetMapping(value = "/getAllByPage")
    @ApiOperation(value = "查询所有-分页")
    public Result<Page<AccessoriesType>> getAllByPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                               @RequestParam(value = "size", defaultValue = "10") Integer size,
                                               @RequestParam(value = "sortFieldName", defaultValue = "id") String sortFieldName,
                                               @RequestParam(value = "asc", defaultValue = "1") Integer asc) {

        return ResultUtil.success(accessoriesTypeService.findAllByPage(page, size, sortFieldName, asc));
    }
}
