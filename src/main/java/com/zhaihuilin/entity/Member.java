package com.zhaihuilin.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.google.gson.Gson;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.awt.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 会员实体类
 * Created by zhaihuilin on 2018/1/31  9:50.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "hafu_member")
@JsonIgnoreProperties({"password","signCode","idCardPicStr","isAccountNonExpired","isAccountNonLocked","isCredentialsNonExpired","isEnabled"})
public class Member implements Serializable,UserDetails {
    @Id
    @GenericGenerator(name = "sys-uid", strategy = "com.zhaihuilin.utils.KeyGeneratorUtils", parameters = {
            @org.hibernate.annotations.Parameter(name = "k", value = "MR")
    })
    @GeneratedValue(generator = "sys-uid")
    private String memberId;


    /**
     * 用户名
     */
    @NotNull
    @Column(length = 36,unique = true)
    private String username;

    /**
     * 用户密码
     */
    @NotNull
    @Column(length = 36)
    private String password;

    /**
     * 昵称
     */
    @Column(length = 20)
    private String nickName;

    /**
     * 真实姓名
     */
    @Column(length = 10)
    private String name;

    /**
     * 联系电话
     */
    @NonNull
    @Column(length = 13)
    private String phone;

    /**
     * E-mail
     */
    private String eMail;


    /**
     * 身份证号
     */
    @Column(length = 18)
    private String idCardNo;


    /**
     * 身份证图片
     */
    @Transient
    private Image idCardPic;


    /**
     * 图片存储字符串
     */
    private String idCardPicStr;


    /**
     * 用户状态
     */
    private String state;


    /**
     * 是否删除
     */
    private boolean del = Boolean.FALSE;

    /**
     * 创建时间
     */
    private long createTime;

    /**
     * 更新时间
     */
    private long updateTime;

    /**
     * 密码加密
     */
    private String signCode;

    /**
     * 是否过期
     */
    private boolean isAccountNonExpired = true;

    /**
     * 是否锁定
     */
    private boolean isAccountNonLocked = true;

    /**
     * 密码是否过期
     */
    private boolean isCredentialsNonExpired = true;

    /**
     * 账户是否激活
     */
    private boolean isEnabled = true;

    public Member(Member member) {
        this.memberId = member.memberId;
        this.username = member.username;
        this.password = member.password;
        this.nickName = member.nickName;
        this.name = member.name;
        this.phone = member.phone;
        this.eMail = member.eMail;
        this.idCardNo = member.idCardNo;
        this.idCardPic = member.idCardPic;
        this.state = member.state;
        this.del = member.del;
        this.createTime = member.createTime;
        this.updateTime = member.updateTime;
        this.signCode = member.signCode;
    }

    /**
     * 所拥有的权限
     */
    @ManyToMany(fetch = FetchType.EAGER,cascade = {})
    @JoinTable(name = "fresh_member_role",joinColumns ={
            @JoinColumn(name = "member_id")
    },inverseJoinColumns = {
            @JoinColumn(name = "role_id")
    })
    @JsonManagedReference
    private List<Role> roleList;

    public Member(String username, String password, String phone) {
        this.username = username;
        this.password = password;
        this.phone = phone;
    }

    /**
     * 简易用户   ZHL
     * @param memberId    用户编号
     * @param username   用户名
     * @param phone  电话
     * @param nickName   昵称
     * @param name   真实姓名
     */
    public Member(String memberId, String username, String phone, String nickName, String name){
        this.memberId=memberId;
        this.username=username;
        this.phone=phone;
        this.nickName=nickName;
        this.name=name;
    }
    /**
     * 权限分配
     */
    @Transient
    private Set<FreshGranteAuthority> authorities = new HashSet<FreshGranteAuthority>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       return this.authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }



    /**
     * 持久化前执行
     */
    @PrePersist
    @PreUpdate
    private void save(){
        if(idCardPic != null){
            Gson gson = new Gson();
            idCardPicStr = gson.toJson(idCardPic);
        }
    }

    /**
     * 读取数据时执行
     */
    @PostLoad
    private void load(){
        if (idCardPicStr != null && idCardPicStr.length() > 0){
            Gson gson = new Gson();
            idCardPic = gson.fromJson(idCardPicStr,Image.class);
            idCardPicStr = null;
        }
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberId='" + memberId + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nickName='" + nickName + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", eMail='" + eMail + '\'' +
                ", idCardNo='" + idCardNo + '\'' +
                ", idCardPic=" + idCardPic +
                ", idCardPicStr='" + idCardPicStr + '\'' +
                ", state='" + state + '\'' +
                ", del=" + del +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", signCode='" + signCode + '\'' +
                '}';
    }
}
