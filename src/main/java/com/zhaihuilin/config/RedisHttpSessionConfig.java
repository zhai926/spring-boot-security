package com.zhaihuilin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

/**
 * session 共享
 * Created by zhaihuilin on 2018/1/30  16:08.
 */
@Configuration
@EnableRedisHttpSession   //开启redis集中式session管理
public class RedisHttpSessionConfig {

    @Bean
     public CookieSerializer cookieSerializer(){
         DefaultCookieSerializer  serializer =new DefaultCookieSerializer();
         serializer.setCookieName("JSESSIONID");//规定 cookie 的名称。
         serializer.setCookiePath("/");//规定 cookie 的服务器路径。
         serializer.setDomainNamePattern("^.+?\\.(\\w+\\.[a-z]+)$");//规定 cookie 的服务器路径。
         return serializer;
     }
























}
