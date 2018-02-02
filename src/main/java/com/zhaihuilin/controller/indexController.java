package com.zhaihuilin.controller;

import com.zhaihuilin.utils.SecurityUtils;
import lombok.extern.log4j.Log4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhaihuilin on 2018/1/31  17:07.
 */
@RestController
@Log4j
public class indexController {

    @RequestMapping(value ="/index")
    public ModelAndView index(){
        ModelAndView modelAndView =new ModelAndView("index")  ;
        log.info("----------------》进到首页");
        return modelAndView;
    }

    /**
     * 自定义登录路劲 必须指定 不然总会一直跑验证相关代码
     * @return
     */
    @RequestMapping(value ="/login")
    public ModelAndView login(
    ){
        ModelAndView modelAndView =new ModelAndView("login")  ;
        log.info("----------------》进到登录页面");
        return modelAndView;
    }


    @RequestMapping("/hello")
    public ModelAndView hello(HttpServletRequest request){
        log.info("----------------》进到欢迎页");
        ModelAndView modelAndView =new ModelAndView("hello");
        String username=SecurityUtils.getUsername(request);
        log.info("获取的当前的登录用户-----------》："+username);
        modelAndView.addObject("username",username);
        return  modelAndView;
    }

}
