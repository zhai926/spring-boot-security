package com.zhaihuilin.utils;

/**
 * 字段非空判断工具类
 * Created by zhaihuilin on 2018/1/30  16:22.
 */
public class StringUtils {

    public static  boolean isNotEmpty(String str){
        if (str !=null && str.trim().length() !=0){
            return  true;
        }
        return false;
    }
}
