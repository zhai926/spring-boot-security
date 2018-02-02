package com.zhaihuilin.service;


import com.zhaihuilin.entity.comment.UploadInfo;
import org.springframework.web.multipart.MultipartFile;


/**
 * 上传逻辑类
 * Created by SunHaiyang on 2017/8/22.
 */
public interface ImageUploadService {

    /**
     * 上传单个
     * @return
     */
    public UploadInfo upload(MultipartFile multipartFile);



}
