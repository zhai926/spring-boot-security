package com.zhaihuilin.dao;


import com.zhaihuilin.entity.Member;
import com.zhaihuilin.entity.MemberInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 用户配置信息持久层
 * Created by HuangXueheng on 2017/9/19.
 */
@Repository
public interface MemberInfoRepository extends JpaRepository<MemberInfo,String> {

    /**
     * 通过用户查找用户配置信息
     * @param member
     * @return
             */
    public MemberInfo findMemberInfoByMember(Member member);

    public MemberInfo findByUsername(String username);

    public Page<MemberInfo> findAllByState(String state, Pageable pageable);
}
