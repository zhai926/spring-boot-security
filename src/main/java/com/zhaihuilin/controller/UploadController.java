package com.zhaihuilin.controller;

import com.zhaihuilin.entity.comment.RequestState;
import com.zhaihuilin.entity.comment.ReturnMessages;
import com.zhaihuilin.entity.comment.UploadInfo;
import com.zhaihuilin.service.ImageUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by SunHaiyang on 2017/8/22.
 */
@RestController
public class UploadController {

    @Autowired
    ImageUploadService uploadService;

    @PostMapping(value = "/upload")
    public ReturnMessages upload(MultipartFile file){
        UploadInfo uploadInfo = uploadService.upload(file);
        if(uploadInfo != null){
            return new ReturnMessages(RequestState.SUCCESS,"上传成功。",uploadInfo);
        }
        return new ReturnMessages(RequestState.SUCCESS,"上传失败。",null);
    }
}
