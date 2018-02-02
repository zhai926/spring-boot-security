package com.zhaihuilin.controller;

import com.google.gson.Gson;
import com.zhaihuilin.entity.Member;
import com.zhaihuilin.entity.comment.RequestState;
import com.zhaihuilin.entity.comment.ReturnMessages;
import com.zhaihuilin.service.MemberService;
import com.zhaihuilin.service.RoleService;
import com.zhaihuilin.utils.MD5Util;
import com.zhaihuilin.utils.StringUtils;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.util.Date;

/**
 * 用户控制层
 * Created by zhaihuilin on 2018/1/31  13:12.
 */
@RestController
@Log4j
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private RoleService roleService;


    /**
     * 注册 用户
     * @param username 用户名
     * @param password 密码
     * @param nickName 昵称[可空]
     * @param name 真实姓名
     * @param phone 电话
     * @param eMail 邮箱[可空]
     * @param idCardNo 身份证号[可空]
     * @param idCardPicStr 身份证照[可空]
     * @return 返回消息
     */
    @RequestMapping(value = "/register")
    public ReturnMessages saveMember(
            @RequestParam(value = "username" ,required = true) String username,
            @RequestParam(value = "password" ,required = true) String password,
            @RequestParam(value = "nickName" ,required = false) String nickName,
            @RequestParam(value = "name",required = false)String name,
            @RequestParam(value = "phone" ,required = true) String phone,
            @RequestParam(value = "eMail" , required = false) String eMail,
            @RequestParam(value = "idCardNo" ,required = false) String idCardNo,
            @RequestParam(value = "idCardPicStr" , required = false) String idCardPicStr,
            HttpServletRequest request
    ){
        ReturnMessages returnMessages = null;
        Member member;
        if(memberService.existMemberbyUsername(username)){
            returnMessages = new ReturnMessages(RequestState.ERROR,"用户名已存在。",null);
        }else {
            if(username.length() < 3){
                return new ReturnMessages(RequestState.ERROR,"用户名长度不可低于3个字符。",null);
            }
            member = new Member(username, MD5Util.string2MD5(password),phone);
            if(StringUtils.isNotEmpty(nickName)){
                member.setNickName(nickName);
            }
            if(StringUtils.isNotEmpty(name)){
                member.setName(name);
            }
            if(StringUtils.isNotEmpty(eMail)){
                member.setEMail(eMail);
            }
            if(StringUtils.isNotEmpty(idCardNo)){
                member.setIdCardNo(idCardNo);
            }
            if (StringUtils.isNotEmpty(idCardPicStr)){
                Gson gson = new Gson();
                member.setIdCardPic(gson.fromJson(idCardPicStr, Image.class));
            }
            member.setCreateTime(new Date().getTime());
            member = memberService.saveMember(member);
            returnMessages = new ReturnMessages(RequestState.SUCCESS,"注册成功。",member);
        }
        return returnMessages;
    }
}
