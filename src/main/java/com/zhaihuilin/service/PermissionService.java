package com.zhaihuilin.service;



import com.zhaihuilin.entity.Permission;

import java.util.List;

/**
 * 权限逻辑类
 * Created by zhaihuilin on 2018/1/31  9:50.
 */
public interface PermissionService {

    /**
     * 获取所有的权限
     * @return List<Permission>
     */
    public List<Permission> findAllByPermission();

    /**
     * 通过权限编号获取权限信息
     * @param id 权限编号
     * @return Permission
     */
    public Permission findById(long id);

    /**
     * 新增权限信息
     * @param permission 权限信息
     * @return Permission
     */
    public Permission savePermission(Permission permission);

    /**
     * 修改权限
     * @param permission
     * @return Permission
     */
    public Permission updatePermission(Permission permission);

    /**
     * 根据权限编号删除权限
     * @param id  权限编号
     * @return   true| false
     */
    public boolean deletePermission(long id);

}
