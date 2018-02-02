package com.zhaihuilin.service;

import com.zhaihuilin.entity.Member;

import java.util.List;

/**
 *
 * Created by zhaihuilin on 2018/1/31  10:34.
 */
public interface MemberService  {

    /**
     * 根据用户编号进行查询
     * @param memberId  用户编号
     * @return  Member
     */
    public Member findMemberByMemberId(String memberId);


    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @return  Member
     */
    public  Member  findMemberByUsername(String username);

    /**
     * 新增用户
     * @param member  用户
     * @return    Member
     */
    public Member saveMember(Member member);

    /**
     * 编辑用户
     * @param member  用户
     * @return Member
     */
    public Member updateMember(Member member);

    /**
     * 查询所有的用户信息
     * @return  List<Member>
     */
    public List<Member> findMemberAll();

    /**
     * 验证用户名是否存在
     * @param username  用户名
     * @return  true|fasle
     */
    public boolean existMemberbyUsername(String username);
}
