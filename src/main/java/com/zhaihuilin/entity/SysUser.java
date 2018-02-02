package com.zhaihuilin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Spring Security 提供了一个实现了可以缓存 UserDetails 的 UserDetailsService 实现类，
 * 封装的一个系统用户
 * Created by zhaihuilin on 2018/1/31  9:50.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SysUser implements UserDetails {

    private String username;  //用户名
    private String password; // 密码
    private List<Role> roleList; // 角色集合
    private List<Permission> permissions; // 权限集合

    public SysUser(Member member, List<Role> roleList) {
        this.username = member.getUsername();
        this.password = member.getPassword();
        this.roleList = roleList;
    }

    /**
     * 返回用于对用户进行身份验证的密码。
     * @return  password
     */
    @Override
    public String getPassword() {
        return this.password;
    }

    /**
     * 返回用于对用户进行身份验证的用户名。
     * @return  username
     */
    @Override
    public String getUsername() {
        return this.username;
    }

    /**
     * 返回给用户的权限
     * @return权限，按自然键排序(从不null)
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
        if(roleList !=null && roleList.size()> 0){
            for (Role role:roleList){
                if(role.getPermissionList() != null && role.getPermissionList().size() > 0){
                    for (Permission permission : role.getPermissionList()){
                        grantedAuthorities.add(new FreshGranteAuthority(permission.getUrl(),permission.getMethod(),role.getRoleCode()));
                    }
                }else{
                    grantedAuthorities.add(new FreshGranteAuthority("","",role.getRoleCode()));
                }
            }
        }else{
            grantedAuthorities.add(new FreshGranteAuthority("","","ROLE:USER:GHOST"));
        }
        return grantedAuthorities;
    }

    /**
     * 身份验证。
     * 指示用户帐户是否已过期。过期的帐户不能。
     * @return true，如果用户的帐户有效(ie未过期)，
     * <代码>假，如果不再有效(即过期)
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 身份验证。
     * 指示用户是否锁定或解锁。锁定的用户不能。
     * 如果用户未被锁定，则<代码>true。否则， false。
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     *指示用户的凭据(密码)是否已过期。过期的
     *防止身份验证凭证。
     * @return true，如果用户的凭证是有效的(ie未过期)，
     * <代码>假，如果不再有效(即过期)
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 身份验证。
     * 指示用户是否启用或禁用。不能使用禁用的用户。
     * @return <代码>true，如果用户启用，false否则
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
