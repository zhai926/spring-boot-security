package com.zhaihuilin.service.security;

import com.zhaihuilin.entity.Member;
import com.zhaihuilin.entity.Role;
import com.zhaihuilin.entity.SysUser;
import com.zhaihuilin.service.MemberService;
import com.zhaihuilin.service.RoleService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Spring Security 提供了一个实现了可以缓存 UserDetails 的 UserDetailsService 实现类
 * Created by zhaihuilin on 2018/1/31  9:50.
 */
@Service
@Log4j
public class FreshDetailsService implements UserDetailsService {
    @Autowired
    MemberService memberService;

    @Autowired
    RoleService roleService;

    /**
     * 登录时获取用户权限,并提交需验证的账号密码
     * @param s
     * @return User
     * @throws UsernameNotFoundException 用户不存在
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        try {
            log.info("------------------>获取的当前的登录用户名:"+s);
            Member member = memberService.findMemberByUsername(s);
            List<Role> roles = roleService.findRoleByMember(member);
            return new SysUser(member,roles);
        }catch (NullPointerException e){
            throw new UsernameNotFoundException("username not exist : " + s);
        }
    }
}
