package com.zhaihuilin.service.impl;

import com.zhaihuilin.dao.MemberRepository;
import com.zhaihuilin.entity.Member;
import com.zhaihuilin.service.MemberService;
import com.zhaihuilin.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
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

    @Override
    public Page<Member> findAllByMember(Member member, Pageable pageable) {
        Page<Member> members = memberRepository.findAll(memberWhere(member),pageable);
        return members;
    }

    @Override
    public boolean deleteByMemberId(String memberId) {
        try {
            //删除用户时，关联删除店铺
            Member m = memberRepository.findMemberByMemberIdAndDelFalse(memberId);
            memberRepository.deleteByMemberId(memberId);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean physicallyDeleteByMemberId(String memberId) {
        try{
            //删除用户时，关联删除店铺
            Member m = memberRepository.findMemberByMemberIdAndDelFalse(memberId);
            memberRepository.delete(memberId);
            return true;
        }catch (Exception e){
            return false;
        }

    }

    /**
     * 条件查询
     * @param member
     * @return
     */
    public static Specification<Member> memberWhere(
            final Member member
    ){
        return new Specification<Member>() {
            @Override
            public Predicate toPredicate(Root<Member> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                List<Predicate> predicates = new ArrayList<Predicate>();
                if(member.getMemberId() != null && !member.getMemberId().equals("")){
                    predicates.add(cb.equal(root.<String>get("memberId"),member.getMemberId()));
                }
                if(member.getUsername() != null && !member.getUsername().equals("")){
                    predicates.add(cb.like(root.<String>get("username"),"%"+member.getUsername()+"%"));
                }
                if(member.getNickName() != null && !member.getNickName().equals("")){
                    predicates.add(cb.like(root.<String>get("nickName"),"%"+member.getNickName()+"%"));
                }
                if(StringUtils.isNotEmpty(member.getName())){
                    predicates.add(cb.like(root.<String>get("name"),"%"+member.getName()+"%"));
                }
                if (member.getEMail() != null && !member.getEMail().equals("")){
                    predicates.add(cb.like(root.<String>get("eMail"),"%"+member.getEMail()+"%"));
                }
                if (member.getIdCardNo() != null && !member.getIdCardNo().equals("")){
                    predicates.add(cb.like(root.<String>get("idCardNo"),"%"+member.getIdCardNo()+"%"));
                }
                if(member.getPhone() != null && !member.getPhone().equals("")){
                    predicates.add(cb.like(root.<String>get("phone"),"%"+member.getPhone()+"%"));
                }
                if(member.getState() != null && !member.getState().equals("")){
                    predicates.add(cb.equal(root.<String>get("state"),member.getState()));
                }
                predicates.add(cb.equal(root.<Boolean>get("del"),member.isDel()));
                return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
            }
        };
    }

}
