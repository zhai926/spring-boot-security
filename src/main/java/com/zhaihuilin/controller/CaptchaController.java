package com.zhaihuilin.controller;

import com.google.code.kaptcha.Producer;
import com.zhaihuilin.utils.JedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;

/**
 * 验证码请求
 * Created by zhaihuilin on 2018/2/1  14:42.
 */
public class CaptchaController {

    @Autowired
    private Producer Captchaproducer;

    @Autowired
    private JedisUtils jedisUtils;

    @GetMapping(value = "/code")
    public ModelAndView getKaptchaImage(
            @RequestParam(value = "key")String key, @RequestParam(value = "time")String random,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        response.setDateHeader("Expires", 0);

        // 设置标准HTTP/1.1无缓存头文件。
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");

        // 设置IE扩展的HTTP/1.1无缓存标题(使用addHeader)。
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");

        // 设置标准HTTP/1.0无缓存头。
        response.setHeader("Pragma", "no-cache");

        // 返回一个jpeg
        response.setContentType("image/jpeg");

        // 为图像创建文本。
        String capText = Captchaproducer.createText();

        System.out.println("******************Key: " + key + "******************");
        System.out.println("******************验证码是: " + capText + "******************");

        jedisUtils.set(key,capText,60);

        // 用文本创建图像。
        BufferedImage bi = Captchaproducer.createImage(capText);

        ServletOutputStream out = response.getOutputStream();

        // 写数据
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
        return null;
    }















































}
