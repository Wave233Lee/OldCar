package com.example.oldcar.controller;

import com.example.oldcar.domain.LeaseOrder;
import com.example.oldcar.domain.Result;
import com.example.oldcar.service.LeaseOrderService;
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
@RequestMapping(value = "/leaseOrder")
@Api(tags = "租赁订单接口")
public class LeaseOrderController {
    @Autowired
    private LeaseOrderService leaseOrderService;

    /**
     * 新增
     */
    @PostMapping(value = "/add")
    public Result<LeaseOrder> add(@Valid LeaseOrder informationContent, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
        return ResultUtil.success(leaseOrderService.add(informationContent));
    }

    /**
     * 更新
     */
    @PostMapping(value = "/update")
    public Result<LeaseOrder> update(@Valid LeaseOrder informationContent){
        return ResultUtil.success(leaseOrderService.update(informationContent));
    }

    /**
     * 根据id查询
     */
    @GetMapping(value = "/getById")
    public Result<LeaseOrder> findById(@RequestParam Long id){
        return ResultUtil.success(leaseOrderService.findById(id));
    }

    /**
     * 查询所有
     */
    @GetMapping(value = "/getAll")
    public Result<List<LeaseOrder>> getAll(){
        return ResultUtil.success(leaseOrderService.findAll());
    }

    /**
     * 批量删除
     */
    @DeleteMapping(value = "/deleteByIds")
    public Result<Object> deleteByIds(@RequestParam Long[] ids) {
        leaseOrderService.deleteInBatch(ids);
        return ResultUtil.success();
    }

    /**
     * 查询所有-分页
     */
    @GetMapping(value = "/getAllByPage")
    @ApiOperation(value = "查询所有-分页")
    public Result<Page<LeaseOrder>> getAllByPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                @RequestParam(value = "sortFieldName", defaultValue = "id") String sortFieldName,
                                                @RequestParam(value = "asc", defaultValue = "1") Integer asc) {

        return ResultUtil.success(leaseOrderService.findAllByPage(page, size, sortFieldName, asc));
    }
}
