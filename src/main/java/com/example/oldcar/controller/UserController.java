package com.example.oldcar.controller;

import com.example.oldcar.domain.Result;
import com.example.oldcar.domain.User;
import com.example.oldcar.service.UserService;
import com.example.oldcar.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * 说明:
 *
 * @author WaveLee
 * 日期: 2018/12/12
 */
@RestController
@RequestMapping(value = "/user")
@Api(tags = "用户接口")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 新增
     */
    @PostMapping(value = "/add")
    @ApiOperation(value = "新增", notes = "id自增长不需要传参")
    public Result<User> add(@Valid User login, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResultUtil.error(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }

        return ResultUtil.success(userService.save(login));
    }

    /**
     * 更新
     */
    @PostMapping(value = "/update")
    @ApiOperation(value = "更新")
    public Result<User> update(@Valid User login, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResultUtil.error(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }

        return ResultUtil.success(userService.update(login));
    }

    /**
     * 通过id删除
     */
    @DeleteMapping(value = "/deleteById")
    @ApiOperation(value = "通过id删除")
    public Result<Object> deleteById(Long id) {
        userService.delete(id);
        return ResultUtil.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping(value = "/deleteByIdBatch")
    @ApiOperation(value = "通过id数组批量删除")
    public Result<Object> deleteByIdBatch(@RequestBody Collection<User> users) {
        userService.deleteInBatch(users);
        return ResultUtil.success();
    }

    /**
     * 通过id查询
     */
    @GetMapping(value = "/getById")
    @ApiOperation(value = "通过id查询")
    public Result<User> getById(Long id) {
        return ResultUtil.success(userService.findOne(id));
    }

    /**
     * 查询所有
     */
    @GetMapping(value = "/getAll")
    @ApiOperation(value = "查询所有")
    public Result<List<User>> getAll() {
        return ResultUtil.success(userService.findAll());

    }

    /**
     * 查询所有-分页
     */
    @GetMapping(value = "/getAllByPage")
    @ApiOperation(value = "查询所有-分页")
    public Result<Page<User>> getAllByPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                            @RequestParam(value = "size", defaultValue = "10") Integer size,
                                            @RequestParam(value = "sortFieldName", defaultValue = "id") String sortFieldName,
                                            @RequestParam(value = "asc", defaultValue = "1") Integer asc) {

        return ResultUtil.success(userService.findAllByPage(page, size, sortFieldName, asc));
    }
}
