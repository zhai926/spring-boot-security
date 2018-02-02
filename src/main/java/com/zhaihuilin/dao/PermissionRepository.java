package com.zhaihuilin.dao;


import com.zhaihuilin.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 权限持久层
 * Created by SunHaiyang on 2017/9/23.
 */
@Repository
public interface PermissionRepository extends JpaRepository<Permission,Long> {

        /**
         * 获取权限上下级
         * @return
         */
        public List<Permission> findAllByOldIsNull();
}
