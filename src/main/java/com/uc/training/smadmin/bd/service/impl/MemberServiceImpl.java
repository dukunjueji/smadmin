package com.uc.training.smadmin.bd.service.impl;

import com.uc.training.common.enums.OrderEnum;
import com.uc.training.smadmin.bd.dao.MemberDao;
import com.uc.training.smadmin.bd.model.LoginLog;
import com.uc.training.smadmin.bd.model.Member;
import com.uc.training.smadmin.bd.mq.MqProducer;
import com.uc.training.smadmin.bd.re.MemberDetailRE;
import com.uc.training.smadmin.bd.re.MemberInfoRE;
import com.uc.training.smadmin.bd.service.LoginLogService;
import com.uc.training.smadmin.bd.service.MemberService;
import com.uc.training.smadmin.bd.vo.*;
import com.uc.training.smadmin.gds.service.GoodsService;
import com.uc.training.smadmin.gds.vo.GoodsStokeVO;
import com.uc.training.smadmin.ord.re.OrderConfirmRE;
import com.uc.training.smadmin.ord.service.OrderService;
import com.uc.training.smadmin.ord.vo.OrdOrderVo;
import com.uc.training.smadmin.utils.EncryptUtil;
import com.ycc.tools.middleware.metaq.MetaQUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderService orderService;

    @Autowired
    private LoginLogService loginLogService;

    @Override
    public void insertMember(Member member) {
        String encryptPaswd = EncryptUtil.md5(member.getPassword());
        member.setPassword(encryptPaswd);
        memberDao.insertMember(member);
    }

    @Override
    public Member queryOneMember(Member member) {
        if (member.getPassword() != null) {
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

    /**
     * 查询余额
     *
     * @param orderPayInfoNow
     */
    @Override
    public List<OrderConfirmRE> queryBalances(List<MemberInfoVO> orderPayInfoNow) {
        List<OrderConfirmRE> list = new ArrayList<>();
        OrderConfirmRE orderConfirmRE = new OrderConfirmRE();
        MemberInfoVO memberInfoVO = new MemberInfoVO();
        memberInfoVO.setTotalPrice(orderPayInfoNow.get(0).getTotalPrice());
        memberInfoVO.setOrderName(orderPayInfoNow.get(0).getOrderName());
        memberInfoVO.setMemberId(orderPayInfoNow.get(0).getMemberId());
        Double accountBalances = memberDao.queryBalances(memberInfoVO.getMemberId());
        if (accountBalances > memberInfoVO.getTotalPrice()) {
            // 加上对应的商品销量
            for (int i = 1, j = orderPayInfoNow.size(); i < j; i++) {
                GoodsStokeVO goodsStokeVO = new GoodsStokeVO();
                goodsStokeVO.setStoke(orderPayInfoNow.get(i).getGoodsNum());
                goodsStokeVO.setGoodsId(orderPayInfoNow.get(i).getGoodsId());
                goodsService.updateSales(goodsStokeVO);
            }
            //减去用户余额
            MemberBalanceVO memberBalanceVO = new MemberBalanceVO();
            memberBalanceVO.setMemberId(memberInfoVO.getMemberId());
            memberBalanceVO.setTotalMoney(memberInfoVO.getTotalPrice());
            memberDao.updateBalance(memberBalanceVO);
            //加成长值，积分

            //更新订单状态
            orderConfirmRE.setStatus(OrderEnum.WAITSHIP.getKey());
            OrdOrderVo ordOrderVo = new OrdOrderVo();
            ordOrderVo.setOrderNum(orderPayInfoNow.get(0).getOrderName());
            ordOrderVo.setStatus(OrderEnum.WAITSHIP.getKey().longValue());
            orderConfirmRE.setShowStatus("成功购买商品");
            orderService.updateOrder(ordOrderVo);
            list.add(orderConfirmRE);
            return list;
        } else {
            orderConfirmRE.setShowStatus("余额不足，请充值或者返回购物车重新选取商品");
            list.add(orderConfirmRE);
            orderConfirmRE.setStatus(OrderEnum.WAITPAY.getKey());
            return list;
        }
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

    @Override
    public List<Member> getMemberList(MemberListVO memberListVO) {
        return memberDao.getMemberList(memberListVO);
    }

    @Override
    public Long queryMemberCount(MemberListVO memberListVO) {
        return memberDao.queryMemberCount(memberListVO);
    }

    @Override
    public void updateBalance(MemberBalanceVO memberBalanceVO) {
        this.memberDao.updateBalance(memberBalanceVO);
    }

    @Override
    public void memberLogin(LoginLog loginLog, MqVO mqVO) {
        loginLogService.insertLog(loginLog);
        //判断是否第一次登陆
        Integer loginNum = loginLogService.queryLoginCount(loginLog);
        if (loginNum == 1){
            MetaQUtils.sendMsgNoException(new MqProducer(mqVO));
        }
    }

    @Override
    public Member queryMemberTel(Long memberId) {
        return memberDao.queryMemberTel(memberId);
    }

    @Override
    public void memberRecharge(Member member, MqVO mqVO) {
          this.updateMemberBalance(member);
          MetaQUtils.sendMsgNoException(new MqProducer(mqVO));
    }

}
