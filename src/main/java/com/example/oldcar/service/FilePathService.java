package com.example.oldcar.service;

import com.example.oldcar.domain.FilePath;
import com.example.oldcar.exception.CarException;
import com.example.oldcar.exception.EnumExceptions;
import com.example.oldcar.repository.FilePathRepository;
import com.example.oldcar.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * 说明:
 *
 * @author WaveLee
 * 日期: 2018/12/20
 */
@Service
public class FilePathService {
    @Autowired
    private FilePathRepository filePathRepository;

    /**
     * 上传
     * @param file
     * @param path
     * @return
     */
    public FilePath Upload(MultipartFile file,String path) {
        if(!file.isEmpty()) {
            // 获取文件名称,包含后缀
            String fileName = file.getOriginalFilename();

            try {
                // 该方法是对文件写入的封装，在util类中
                FileUtil.fileupload(file, path, fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // 接着创建对应的实体类，将以下路径进行添加，然后通过数据库操作方法写入
            FilePath filePath = new FilePath();
            String server = "111.230.34.50:8080";
//            String server = "127.0.0.1:8080";
            filePath.setPath("http://"+ server +"/oldcar/img/"+ path + fileName);
            return filePathRepository.save(filePath);
        }else
            throw new CarException(EnumExceptions.UPLOAD_FAILED_EMPUTY);
    }

    /**
     * 根据id查询
     */
    public FilePath findById(Long id){
        Optional<FilePath> optional = filePathRepository.findById(id);
        return optional.orElse(null);
    }

    /**
     * 查询所有
     */
    public List<FilePath> findAll() {
        return filePathRepository.findAll();
    }


}
