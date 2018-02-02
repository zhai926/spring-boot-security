package com.zhaihuilin.service.impl;

import com.zhaihuilin.dao.MemberSettingRepository;
import com.zhaihuilin.entity.Member;
import com.zhaihuilin.entity.MemberSetting;
import com.zhaihuilin.service.MemberSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by SunHaiyang on 2017/8/25.
 */
@Service
public class MemberSettingServiceImpl implements MemberSettingService {

    @Autowired
    MemberSettingRepository memberSettingRepository;

    @Override
    public MemberSetting findMemberSettingByMember(Member member) {
        return memberSettingRepository.findByMember(member);
    }

    @Override
    public MemberSetting saveMemberSetting(MemberSetting memberSetting) {
        return memberSettingRepository.save(memberSetting);
    }

    @Override
    public MemberSetting updateMemberSetting(MemberSetting memberSetting) {
        return memberSettingRepository.save(memberSetting);
    }

    @Override
    public MemberSetting findMemberSettingByMemberAndDay(Member member, int day) {
        return memberSettingRepository.findByMemberAndSettlementDate(member,day);
    }

    @Override
    public List<MemberSetting> findMemberSettingBysettlementDate(int day) {
        return memberSettingRepository.findMemberSettingBysettlementDate(day);
    }

    @Override
    public List<MemberSetting> findMemberSettingBySettlementInterval(int day) {
        return memberSettingRepository.findMemberSettingBySettlementInterval(day);
    }
}
