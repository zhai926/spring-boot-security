package com.zhaihuilin.entity.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 访问状态
 * Created by zhaihuilin on 2018/1/31  9:50.
 */
@AllArgsConstructor
@Getter
public enum RequestState {

    /**
     * 访问出错
     */
    ERROR(400),
    /**
     * 访问成功
     */
    SUCCESS(200),
    /**
     * 权限异常
     */
    AUTHENTICATION_ERROR(403);

    /**
     * 状态码
     */
    private final int stateCode;


}
