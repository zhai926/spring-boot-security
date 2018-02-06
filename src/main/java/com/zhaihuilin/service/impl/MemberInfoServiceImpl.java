package com.zhaihuilin.service.impl;


import com.zhaihuilin.dao.MemberInfoRepository;
import com.zhaihuilin.entity.Member;
import com.zhaihuilin.entity.MemberInfo;
import com.zhaihuilin.service.MemberInfoService;
import com.zhaihuilin.service.MemberService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户配置信息 逻辑层
 * Created by zhaihuilin on 2018/1/31  9:50.
 */
@Transactional
@Service
@Log4j
public class MemberInfoServiceImpl implements MemberInfoService {

    @Autowired
    MemberInfoRepository memberInfoRepository;

    @Autowired
    MemberService memberService;


    /**
     * 保存用户配置信息
     * @param memberInfo
     * @return
     */
    @Override
    public MemberInfo saveMemberInfo(MemberInfo memberInfo) {

        return memberInfoRepository.save(memberInfo);
    }

    /**
     * 更改用户配置信息
     * @param memberInfo
     * @return
     */
    @Override
    public MemberInfo updateMemberInfo(MemberInfo memberInfo) {

        return memberInfoRepository.save(memberInfo);
    }

    /**
     * 查找用户配置信息
     * @param member
     * @return
     */
    @Override
    public MemberInfo findMemberInfoByMember(Member member) {
        return memberInfoRepository.findMemberInfoByMember(member);
    }

    @Override
    public Page<MemberInfo> findMemberInfo(Pageable pageable) {
        return memberInfoRepository.findAll(pageable);

    }

    @Override
    public MemberInfo findMemberInfoByUsername(String username) {
        Member member = memberService.findMemberByUsername(username);
        MemberInfo memberInfo = memberInfoRepository.findByUsername(username);
        if(memberInfo == null){
            return null;
        }
        if(member != null){
            memberInfo.setMember(member);
        }
        return memberInfo;
    }

    @Override
    public Page<MemberInfo> findMemberInfoByState(String state, Pageable pageable) {
        return memberInfoRepository.findAllByState(state,pageable);
    }

}
