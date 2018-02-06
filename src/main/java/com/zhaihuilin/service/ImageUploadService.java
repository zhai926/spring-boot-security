package com.zhaihuilin.service;


import com.zhaihuilin.entity.comment.UploadInfo;
import org.springframework.web.multipart.MultipartFile;


/**
 * 上传逻辑类
 * Created by zhaihuilin on 2018/1/31  9:50.
 */
public interface ImageUploadService {

    /**
     * 上传单个
     * @return
     */
    public UploadInfo upload(MultipartFile multipartFile);



}
