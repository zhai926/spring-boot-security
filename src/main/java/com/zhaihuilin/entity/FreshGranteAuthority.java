package com.zhaihuilin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

/**
 * 自定义权限类
 * Created by zhaihuilin on 2018/1/31  9:50.
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FreshGranteAuthority  implements GrantedAuthority{

    /**
     * 地址
     */
    private String url;

    /**
     * 链接方式
     */
    private String method;
    /**
     * 权限
     */
    private String authority;

    @Override
    public String getAuthority() {
        return this.authority;
    }
}
