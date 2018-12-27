package com.example.oldcar.controller;

import com.example.oldcar.domain.Result;
import com.example.oldcar.domain.VideoHeader;
import com.example.oldcar.service.VideoHeaderService;
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
@RequestMapping(value = "/video")
@Api(tags = "视频接口")
public class VideoController {
    @Autowired
    private VideoHeaderService videoHeaderService;

    /**
     * 新增
     */
    @PostMapping(value = "/add")
    public Result<VideoHeader> add(@Valid VideoHeader informationContent, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
        return ResultUtil.success(videoHeaderService.add(informationContent));
    }

    /**
     * 更新
     */
    @PostMapping(value = "/update")
    public Result<VideoHeader> update(@Valid VideoHeader informationContent){
        return ResultUtil.success(videoHeaderService.update(informationContent));
    }

    /**
     * 根据id查询
     */
    @GetMapping(value = "/getById")
    public Result<VideoHeader> findById(@RequestParam Long id){
        return ResultUtil.success(videoHeaderService.findById(id));
    }

    /**
     * 查询所有
     */
    @GetMapping(value = "/getAll")
    public Result<List<VideoHeader>> getAll(){
        return ResultUtil.success(videoHeaderService.findAll());
    }

    /**
     * 批量删除
     */
    @DeleteMapping(value = "/deleteByIds")
    public Result<Object> deleteByIds(@RequestParam Long[] ids) {
        videoHeaderService.deleteInBatch(ids);
        return ResultUtil.success();
    }

    /**
     * 查询所有-分页
     */
    @GetMapping(value = "/getAllByPage")
    @ApiOperation(value = "查询所有-分页")
    public Result<Page<VideoHeader>> getAllByPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                           @RequestParam(value = "size", defaultValue = "10") Integer size,
                                           @RequestParam(value = "sortFieldName", defaultValue = "id") String sortFieldName,
                                           @RequestParam(value = "asc", defaultValue = "1") Integer asc) {

        return ResultUtil.success(videoHeaderService.findAllByPage(page, size, sortFieldName, asc));
    }
}
