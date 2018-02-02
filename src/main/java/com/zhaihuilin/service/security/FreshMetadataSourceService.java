package com.zhaihuilin.service.security;

import lombok.extern.log4j.Log4j;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.HashSet;

/**
 * Created by SunHaiyang on 2017/8/4.
 */
@Service
@Log4j
public class FreshMetadataSourceService implements FilterInvocationSecurityMetadataSource {
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        final HttpServletRequest request = ((FilterInvocation)o).getHttpRequest();
        log.info("-----获取的url-------->"+request.getRequestURL());
        Collection<ConfigAttribute> configAttributes = new HashSet<ConfigAttribute>();
        configAttributes.add(new SecurityConfig("null"));
        return configAttributes;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
