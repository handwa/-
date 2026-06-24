package com.sky.controller.admin;

import com.sky.constant.MessageConstant;
import com.sky.result.Result;
import com.sky.utils.AliOssUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/admin/common")
@Slf4j
public class CommonController {

    @Autowired
    private AliOssUtil aliOssUtil;
    /**
     * 文件上传
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile  file){
        log.info("文件上传：{}",file);
        try {
            // 1. 提取原始文件后缀
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            // 2. 定义OSS存储目录，可自行修改：product/ 、avatar/、category/
            String folder = "product/";
            // 3. 拼接完整对象名：目录 + UUID文件名.后缀
            String objectName = folder + UUID.randomUUID().toString() + extension;
            // 4. 上传到指定目录
            String filePath = aliOssUtil.upload(file.getBytes(),objectName);
            return Result.success(filePath);
        } catch (IOException e) {
            log.error("文件上传失败：{}",e);
        }
        return Result.error(MessageConstant.UPLOAD_FAILED);
    }
}
