package com.zhaihuilin.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Service;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 主要负责授权的工作
 * Created by zhaihuilin on 2018/1/31  9:50.
 */
@Service
public class FreshFilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {

    /**
     * 安全元数据资源
     */
    @Autowired
    FreshMetadataSourceService metadataSourceService;

    /**
     * 授权
     * @param freshAccessDecisionManager
     */
    @Autowired
    public void setAccessDecisionManage(FreshAccessDecisionManager freshAccessDecisionManager){
        super.setAccessDecisionManager(freshAccessDecisionManager);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    private static HttpServletRequest getSessionId(ServletRequest request){
        HttpServletRequest httpRequest=(HttpServletRequest)request;
        return httpRequest;
    }

    /**
     * 当 FilterSecurityInterceptor所依赖的决策管理器、认证管理器、安全元数据资源都具备了 就开始让 其干活了
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest servletRequest = getSessionId(request);
        ////封装request, response, chain，方便参数传递、增加代码阅读性
        FilterInvocation invocation = new FilterInvocation(request,response,chain);
        invoke(invocation);
    }

    public void invoke(FilterInvocation filterInvocation) throws IOException, ServletException {
        ///执行父类beforeInvocation，类似于aop中的before
        InterceptorStatusToken token = super.beforeInvocation(filterInvocation);
        try {
            //filter传递
            filterInvocation.getChain().doFilter(filterInvocation.getHttpRequest(),filterInvocation.getHttpResponse());
        }finally {
            ////执行父类的afterInvocation，类似于aop中的after
            super.afterInvocation(token,null);
        }
    }


    @Override
    public void destroy() {

    }

    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return this.metadataSourceService;
    }
}
