package com.zhaihuilin.config;

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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.session.ExpiringSession;
import org.springframework.session.FindByIndexNameSessionRepository;

/**
 * Created by SunHaiyang on 2017/8/4.
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
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .anyRequest().permitAll().and()
                .formLogin()
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/login") // 自定义登录路劲
                .loginPage("/login")   //自定义登录页面
                .defaultSuccessUrl("/hello")   //自定义登录成功跳转的地址
                .and()
                .logout().logoutUrl("/logout").permitAll();
        http.addFilterBefore(freshFilterSecurityInterceptor, FilterSecurityInterceptor.class);
    }
}

