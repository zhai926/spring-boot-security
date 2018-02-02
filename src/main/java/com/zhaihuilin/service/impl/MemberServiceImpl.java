package com.zhaihuilin.service.impl;

import com.zhaihuilin.dao.MemberRepository;
import com.zhaihuilin.entity.Member;
import com.zhaihuilin.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户逻辑层
 * Created by zhaihuilin on 2018/1/31  10:55.
 */
@Service
@Transactional
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    /**
     * 根据用户编号查询用户
     * @param memberId  用户编号
     * @return Member
     */
    @Override
    public Member findMemberByMemberId(String memberId) {
        return memberRepository.findMemberByMemberIdAndDelFalse(memberId);
    }

    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return Member
     */
    @Override
    public Member findMemberByUsername(String username) {
        return memberRepository.findMemberByUsername(username);
    }

    /**
     * 新增用户信息
     * @param member  用户
     * @return Member
     */
    @Override
    public Member saveMember(Member member) {
        return memberRepository.save(member);
    }

    /**
     * 编辑用户信息
     * @param member  用户
     * @return Member
     */
    @Override
    public Member updateMember(Member member) {
        return memberRepository.save(member);
    }

    /**
     * 获取所有的用户信息
     * @return  List<Member>
     */
    @Override
    public List<Member> findMemberAll() {
        return memberRepository.findAll();
    }

    /**
     * 验证用户名是否存在
     * @param username  用户名
     * @return
     */
    @Override
    public boolean existMemberbyUsername(String username) {
        boolean flag = Boolean.FALSE;
        Member member = memberRepository.findMemberByUsername(username);
        if (member != null){
            flag = Boolean.TRUE;
        }
        return flag;
    }
}
