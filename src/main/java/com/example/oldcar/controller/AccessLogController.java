package com.example.oldcar.controller;

import com.example.oldcar.domain.AccessLog;
import com.example.oldcar.domain.Result;
import com.example.oldcar.service.AccessLogService;
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
 * 日期: 2019/3/6
 */
@RestController
@RequestMapping(value = "/accessLog")
@Api(tags = "用户行为记录日志")
public class AccessLogController {
    @Autowired
    private AccessLogService accessLogService;

    /**
     * 新增
     */
    @PostMapping(value = "/add")
    public Result<AccessLog> add(@Valid AccessLog informationContent, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
        return ResultUtil.success(accessLogService.add(informationContent));
    }

    /**
     * 更新
     */
    @PostMapping(value = "/update")
    public Result<AccessLog> update(@Valid AccessLog informationContent){
        return ResultUtil.success(accessLogService.update(informationContent));
    }

    /**
     * 根据id查询
     */
    @GetMapping(value = "/getById")
    public Result<AccessLog> findById(@RequestParam Long id){
        return ResultUtil.success(accessLogService.findById(id));
    }

    /**
     * 查询所有
     */
    @GetMapping(value = "/getAll")
    public Result<List<AccessLog>> getAll(){
        return ResultUtil.success(accessLogService.findAll());
    }

    /**
     * 批量删除
     */
    @DeleteMapping(value = "/deleteByIds")
    public Result<Object> deleteByIds(@RequestParam Long[] ids) {
        accessLogService.deleteInBatch(ids);
        return ResultUtil.success();
    }

    /**
     * 查询所有-分页
     */
    @GetMapping(value = "/getAllByPage")
    @ApiOperation(value = "查询所有-分页")
    public Result<Page<AccessLog>> getAllByPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                         @RequestParam(value = "size", defaultValue = "10") Integer size,
                                         @RequestParam(value = "sortFieldName", defaultValue = "id") String sortFieldName,
                                         @RequestParam(value = "asc", defaultValue = "1") Integer asc) {

        return ResultUtil.success(accessLogService.findAllByPage(page, size, sortFieldName, asc));
    }

}
