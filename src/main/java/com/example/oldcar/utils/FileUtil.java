package com.example.oldcar.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * 说明:
 *
 * @author WaveLee
 * 日期: 2018/12/20
 */
public class FileUtil {
    //静态方法：三个参数：文件，文件路径，文件名
    //通过该方法将在指定目录下添加指定文件
    public static void fileupload(MultipartFile file, String filePath, String fileName) throws IOException {
        //目标目录
        filePath = "C:/img/" + filePath;
        File dest = new File(filePath + fileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            //假如文件不存在即重新创建新的文件以防止异常发生
            dest.getParentFile().mkdirs();
        }

        //二进制流写入
        file.transferTo(dest);
    }
}
