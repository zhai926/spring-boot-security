package com.zhaihuilin.service.impl;


import com.zhaihuilin.dao.PermissionRepository;
import com.zhaihuilin.entity.Permission;
import com.zhaihuilin.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by SunHaiyang on 2017/9/23.
 */
@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    PermissionRepository permissionRepository;


    /**
     * 获取所有的权限信息
     * @return List<Permission>
     */
    @Override
    public List<Permission> findAllByPermission() {
        return permissionRepository.findAllByOldIsNull();
    }

    /**
     * 根据权限编号进行查询
     * @param id 权限编号
     * @return
     */
    @Override
    public Permission findById(long id) {
        return permissionRepository.findOne(id);
    }

    /**
     * 新增权限
     * @param permission 权限信息
     * @return
     */
    @Override
    public Permission savePermission(Permission permission) {
        return permissionRepository.save(permission);
    }

    /**
     * 修改权限
     * @param permission
     * @return
     */
    @Override
    public Permission updatePermission(Permission permission) {
        return permissionRepository.save(permission);
    }

    /**
     * 删除权限信息
     * @param id  权限编号
     * @return
     */
    @Override
    public boolean deletePermission(long id) {
        try {
            permissionRepository.delete(id);
            return true;
        }catch (Exception e){
            return false;
        }

    }
}
