package com.example.oldcar.controller;

import com.example.oldcar.domain.ArticleHeader;
import com.example.oldcar.domain.Result;
import com.example.oldcar.service.ArticleHeaderService;
import com.example.oldcar.utils.ResultUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(value = "/article")
@Api(tags = "文章接口")
public class ArticleHeaderController {
    @Autowired
    private ArticleHeaderService articleHeaderService;

    /**
     * 新增
     */
    @PostMapping(value = "/add")
    public Result<ArticleHeader> add(@Valid ArticleHeader informationContent, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
        return ResultUtil.success(articleHeaderService.add(informationContent));
    }

    /**
     * 更新
     */
    @PostMapping(value = "/update")
    public Result<ArticleHeader> update(@Valid ArticleHeader informationContent){
        return ResultUtil.success(articleHeaderService.update(informationContent));
    }

    /**
     * 根据id查询
     */
    @GetMapping(value = "/getById")
    public Result<ArticleHeader> findById(@RequestParam Long id){
        return ResultUtil.success(articleHeaderService.findById(id));
    }

    /**
     * 查询所有
     */
    @GetMapping(value = "/getAll")
    public Result<List<ArticleHeader>> getAll(){
        return ResultUtil.success(articleHeaderService.findAll());
    }

    /**
     * 批量删除
     */
    @DeleteMapping(value = "/deleteByIds")
    public Result<Object> deleteByIds(@RequestParam Long[] ids) {
        articleHeaderService.deleteInBatch(ids);
        return ResultUtil.success();
    }

}
