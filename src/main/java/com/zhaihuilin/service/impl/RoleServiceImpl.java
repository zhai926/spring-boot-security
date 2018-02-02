package com.zhaihuilin.service.impl;

import com.zhaihuilin.dao.MemberRepository;
import com.zhaihuilin.dao.RoleRepository;
import com.zhaihuilin.entity.Member;
import com.zhaihuilin.entity.Role;
import com.zhaihuilin.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaihuilin on 2018/1/31  10:58.
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private MemberRepository memberRepository;

    /**
     * 根据角色编号查询角色
     * @param id 角色编号
     * @return  Role
     */
    @Override
    public Role findRolesById(long id) {
        return roleRepository.findRolesById(id);
    }

    /**
     * 新增角色
     * @param role  角色
     * @return  Role
     */
    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    /**
     * 编辑角色
     * @param role  角色
     * @return  Role
     */
    @Override
    public Role updateRole(Role role) {
        return roleRepository.save(role);
    }

    /**
     * 获取所有的角色信息
     * @return  List<Role>
     */
    @Override
    public List<Role> findRoleAll() {
        return roleRepository.findAll();
    }

    /**
     * 设置默认的角色信息
     * @param id   角色编号
     * @return  Role
     */
    @Override
    public Role setRoleDefault(long id) {
        Role role = roleRepository.findRoleByTheDefaultTrue();
        if(role != null){
            role.setTheDefault(false);
            roleRepository.save(role);
        }
        role = roleRepository.findRolesById(id);
        role.setTheDefault(true);
        return roleRepository.save(role);
    }

    /**
     * 获取默认的角色
     * @return  Role
     */
    @Override
    public Role getRoleByDefaule() {
        return roleRepository.findRoleByTheDefaultTrue();
    }

    /**
     * 根据用户信息查询角色
     * @param member  用户
     * @return  List<Role>
     */
    @Override
    public List<Role> findRoleByMember(Member member) {
        List<Member> members = new ArrayList<Member>();
        members.add(member);
        return roleRepository.findRoleByMemberList(members);
    }

    /**
     * 设置用户角色
     * @param username  用户名
     * @param roles  角色集合
     * @return
     */
    @Override
    public boolean setMemberRole(String username, List<Role> roles) {
        Member member = memberRepository.findMemberByUsername(username);
        if(member != null){
            member.setRoleList(roles);
            member = memberRepository.save(member);
            if(member != null){
                return true;
            }
        }
        return false;
    }

    /**
     * 删除角色
     * @param id  角色编号
     * @return
     */
    @Override
    public boolean deleteRole(long id) {
        Role role= roleRepository.findRolesById(id);
        if (role !=null){
            roleRepository.delete(id);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
