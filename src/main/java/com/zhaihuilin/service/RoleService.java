package com.zhaihuilin.service;

import com.zhaihuilin.entity.Member;
import com.zhaihuilin.entity.Role;

import java.util.List;

/**
 * 角色dao 层
 * Created by zhaihuilin on 2018/1/31  10:39.
 */
public interface RoleService {

    /**
     * 根据角色编号查询角色
     * @param id 角色编号
     * @return  Role
     */
    public Role findRolesById(long id);


    /**
     * 新增 角色
     * @param role  角色
     * @return Role
     */
    public Role saveRole(Role role);

    /**
     * 编辑 角色
     * @param role
     * @return Role
     */
    public Role updateRole(Role role);

    /**
     * 查询所有的角色
     * @return List<Role>
     */
    public List<Role> findRoleAll();

    /**
     * 设定为默认角色
     * @param id   角色编号
     * @return  Role
     */
    public Role setRoleDefault(long id);

    /**
     * 获取默认角色
     * @return  Role
     */
    public Role getRoleByDefaule();


    /**
     * 通过用户查询角色
     * @param member  用户
     * @return  List<Role>
     */
    public List<Role> findRoleByMember(Member member);

    /**
     * 赋予用户权限
     * @param username  用户名
     * @param roles  角色集合
     * @return  false/true
     */
    public boolean setMemberRole(String username,List<Role> roles);


    /**
     * 删除角色
     * @param id
     * @return
     */
    public boolean deleteRole(long id);
}
