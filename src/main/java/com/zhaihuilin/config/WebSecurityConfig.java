package com.zhaihuilin.config;

import com.google.gson.Gson;
import com.zhaihuilin.entity.comment.RequestState;
import com.zhaihuilin.entity.comment.ReturnMessages;
import com.zhaihuilin.service.security.FreshDetailsService;
import com.zhaihuilin.service.security.FreshFilterSecurityInterceptor;
import com.zhaihuilin.utils.MD5Util;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.session.ExpiringSession;
import org.springframework.session.FindByIndexNameSessionRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * WebSecurityConfigurerAdapter 提供了一种便利的方式去创建 WebSecurityConfigurer的实例，只需要重写     WebSecurityConfigurerAdapter 的方法，即可配置拦截什么URL、设置什么权限等安全控制。
 * 安全框架
 * Created by zhaihuilin on 2018/1/31  9:50.
 */
@Configuration
@Log4j
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    FreshFilterSecurityInterceptor freshFilterSecurityInterceptor;

    @Autowired
    FindByIndexNameSessionRepository<? extends ExpiringSession> sessionRepository;


    @Bean
    public UserDetailsService freshUserService() {
        return new FreshDetailsService();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(freshUserService()).passwordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return MD5Util.string2MD5(String.valueOf(charSequence));
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                log.info("-------charSequence----->密码: " + charSequence.toString());
                log.info("s-------------->密码:"+s);
                boolean flag = false;
                if (encode(charSequence).equals(s)) {
                    flag = true;
                }
                return flag;
            }
        });
    }

    /**
     *  SecurityContextPersistenceFilter：创建安全上下文SecurityContext
     *  UsernamePasswordAuthenticationFilter：完成认证处理，并把认证通过的实体保存到SecurityContext中
     *  FilterSecurityInterceptor：完成授权处理
     *
     *  SecurityContextPersistenceFilter: 创建一个无认证实体的【SecurityContext】并在filter返回时持久到session 中
     *  ------------> UsernamePasswordAuthenticationFilter : 拦截 j_spring_security_check 获取的登录信息 构造 UsernamePasswordAuthenticationToken 把具体的认证工作交给 AuthenticationManager  然后把 认证通过的的实体保存到 SecurityContext 中
     *  ------------> FilterSecurityInterceptor：进行封装 request  response 为 【FilterInvocation】 根据 request 的 url 获取权限配置 【ConfigAttribute】 集合 从 SecurityContext 提取认证的实体信息 然后把具体的授权的工作 交给 【AccessDecisionManager】来完成  而 AccessDecisionManager 来通过 AffirmativeBased（决策管理器）
—     *
     * 认证过程：
     * AuthenticationFilter --------->AuthenticationManager --------->AuthenticationProvider ----------> UserDetailsService
     * 授权过程：
     * FilterSecurityInterceptor ——————> AccessDecisionManager ——————> AccessDecisionVoter
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .anyRequest().permitAll().and()
                .formLogin()
                .successHandler(
                        new AuthenticationSuccessHandler() {
                            @Override
                            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                                Gson gson = new Gson();
                                ReturnMessages messages = new ReturnMessages(RequestState.SUCCESS, "登录成功。", null);
                                response.setContentType("text/json;charset=utf-8");
                                response.getWriter().write(gson.toJson(messages));
                            }
                        }
                )
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
                        Gson gson = new Gson();
                        ReturnMessages messages = new ReturnMessages(RequestState.ERROR, "用户名或密码错误。", null);
                        httpServletResponse.setContentType("text/json;charset=utf-8");
                        httpServletResponse.getWriter().write(gson.toJson(messages));
                    }
                })
                .and()
                .logout().logoutUrl("/logout").permitAll();
        http.addFilterBefore(freshFilterSecurityInterceptor, FilterSecurityInterceptor.class);
        http.addFilterBefore(new KaptchaAuthenticationFilter("/login","/login?error"),UsernamePasswordAuthenticationFilter.class);
    }
}

