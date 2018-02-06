package com.zhaihuilin.utils;

import org.springframework.security.core.context.SecurityContextImpl;

import javax.servlet.http.HttpServletRequest;

/**
 * 可以获取当前的登录用户名
 * Security简单的工具
 * Created by zhaihuilin on 2018/1/31  9:50.
 */
public class SecurityUtils {

    /**
     * 获取用户名
     * @param request
     * @return
     */
    public static String getUsername(HttpServletRequest request){
        SecurityContextImpl securityContext = (SecurityContextImpl)request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
        if(securityContext == null){
            return null;
        }
        return securityContext.getAuthentication().getName();
    }


}
