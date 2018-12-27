package com.example.oldcar.controller;

import com.example.oldcar.domain.FilePath;
import com.example.oldcar.domain.Result;
import com.example.oldcar.service.FilePathService;
import com.example.oldcar.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 说明:图片接口
 *
 * @author WaveLee
 * 日期: 2018/12/20
 */
@RestController
@Api(tags = "图片上传接口")
public class FileUploadController {
    @Autowired
    private FilePathService filePathService;

    // 传入的参数file是我们指定的文件
    @PostMapping("/upload")
    @ResponseBody
    @ApiOperation(value="上传文件",notes="file为上传文件，filePath为存储路径例如:img/car/")
    public Result<FilePath> upload(@RequestParam("file") MultipartFile file,
                                   @RequestParam("filePath") String filePath) {
        return ResultUtil.success(filePathService.Upload(file,filePath));
    }

    /**
     * 查询所有
     */
    @GetMapping(value = "/getAll")
    @ApiOperation(value="查询所有",notes = "返回文件路径url")
    public Result<List<FilePath>> getAll(){
        return ResultUtil.success(filePathService.findAll());
    }

}
