package com.zhaihuilin.dao;

import com.zhaihuilin.entity.Member;
import com.zhaihuilin.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色 dao
 * Created by zhaihuilin on 2018/1/31  10:31.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

    /**
     * 根据角色编号查询角色
     * @param id 角色编号
     * @return  Role
     */
    public  Role findRolesById(long id);

    /**
     * 根据用户名查询
     * @param name  角色名称
     * @return List<Role>
     */
    public List<Role> findByName(String name);


    /**
     * 设定默认角色
     * @param id   角色编号
     * @return  Role
     */
    @Query(value = "update Role r set r.theDefault = true where r.id = ?1")
    public Role setRoleDefault(long id);

    /**
     * 查询默认角色
     * @return  Role
     */
    public Role findRoleByTheDefaultTrue();


    /**
     * 通过用户查询角色
     * @return List<Role>
     */
    public List<Role> findRoleByMemberList(List<Member> members);
}
