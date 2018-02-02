package com.zhaihuilin.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * redis 工具类
 * Created by zhaihuilin on 2018/1/30  16:16.
 */
@Component
public class JedisUtils {

    @Autowired
    RedisTemplate<Object,Object> redisTemplate;  //注入redis缓存模板

    @Resource(name ="redisTemplate")
    ValueOperations<String,String> valueOperations; //简单的键值对

    /**
     * 设置 键值
     * @param key
     * @param value
     */
    public void set (String key,String value){

        valueOperations.set(key, value);
    }

    public void set(String key,String value,long outTime){
        valueOperations.set(key, value, outTime, TimeUnit.SECONDS);
    }

    /**
     * 获取键
     * @return
     */
    public String get(String key){
        String  value=valueOperations.get(key); //获取值
        if (StringUtils.isNotEmpty(value)){
            return  value;
        }
        return null;
    }
}
