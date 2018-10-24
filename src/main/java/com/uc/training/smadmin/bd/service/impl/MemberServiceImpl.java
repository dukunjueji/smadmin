package com.uc.training.smadmin.bd.service.impl;

import com.uc.training.smadmin.bd.dao.MemberDao;
import com.uc.training.smadmin.bd.model.Member;
import com.uc.training.smadmin.bd.re.MemberDetailRE;
import com.uc.training.smadmin.bd.re.MemberInfoRE;
import com.uc.training.smadmin.bd.service.MemberService;
import com.uc.training.smadmin.bd.vo.ChargeBalanceVO;
import com.uc.training.smadmin.bd.vo.MemberLoginVO;
import com.uc.training.smadmin.utils.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：shixian.zhang@ucarinc.com
 * @version：v1.0
 * @date: 2018/10/16
 * 说明：用户逻辑判断实现类
 */
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao memberDao;

    @Override
    public void insertMember(Member member) {
        String encryptPaswd = EncryptUtil.md5(member.getPassword());
        member.setPassword(encryptPaswd);
        memberDao.insertMember(member);
    }

    @Override
    public Member queryOneMember(Member member) {
        if (member.getPassword() != null){
            //密码加密
            member.setPassword(EncryptUtil.md5(member.getPassword()));
        }
        return memberDao.queryOneMember(member);
    }

    @Override
    public void updateMember(Member member) {
        String newPaswd = EncryptUtil.md5(member.getPassword());
        member.setPassword(newPaswd);
        memberDao.updateMember(member);
    }

    @Override
    public void updateMemberBalance(Member member) {
        memberDao.updateMemberBalance(member);
    }

    @Override
    public MemberDetailRE getMemberDetailById(Long memberId) {
        return memberDao.getMemberDetailById(memberId);
    }

    @Override
    public Member getMemberLogin(MemberLoginVO memberLoginVO) {
        return memberDao.getMemberLogin(memberLoginVO);
    }

    @Override
    public int updateMemberInfo(Member member) {
        return this.memberDao.updateMemberInfo(member);
    }

    @Override
    public MemberInfoRE queryOneMemberById(Long uid) {
        return memberDao.queryOneMemberById(uid);
    }

}
