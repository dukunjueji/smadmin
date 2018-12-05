package com.uc.training.base.bd.service.impl;

import com.uc.training.base.bd.re.MemberDetailRE;
import com.uc.training.base.bd.re.MemberRE;
import com.uc.training.base.bd.service.MemberService;
import com.uc.training.base.bd.vo.LoginVO;
import com.uc.training.base.bd.vo.MemberInfoVO;
import com.uc.training.base.bd.vo.MemberListVO;
import com.uc.training.base.bd.vo.MemberVO;
import com.uc.training.common.enums.GrowthEnum;
import com.uc.training.common.enums.IntegralEnum;
import com.uc.training.common.enums.OrderEnum;
import com.uc.training.common.mq.MqProducer;
import com.uc.training.common.mq.vo.MqVO;
import com.uc.training.common.utils.EncryptUtil;
import com.uc.training.ord.re.OrderConfirmRE;
import com.uc.training.ord.re.OrderRE;
import com.uc.training.ord.service.OrderService;
import com.uc.training.ord.vo.OrdMemberVO;
import com.uc.training.ord.vo.OrdOrderVO;
import com.uc.training.remote.client.BaseClient;
import com.ycc.tools.middleware.metaq.MetaQUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(MemberServiceImpl.class.getName());

    @Autowired
    private OrderService orderService;

    @Override
    public Long insertMember(MemberVO memberVO) {
        return BaseClient.insertMember(memberVO);
    }

    @Override
    public MemberRE queryOneMember(MemberVO member) {
        if (member.getPassword() != null) {
            //密码加密
            member.setPassword(EncryptUtil.md5(member.getPassword()));
        }
        return BaseClient.queryOneMember(member);
    }

    @Override
    public Integer updateMember(MemberVO member) {
        member.setPassword(EncryptUtil.md5(member.getPassword()));
        return BaseClient.updateMember(member);
    }

    /**
     * 查询余额/确认支付
     *
     * @param orderPayInfoNow
     */
    @Override
    public List<OrderConfirmRE> queryBalances(List<MemberInfoVO> orderPayInfoNow, MqVO mqVO) {
        List<OrderConfirmRE> list = new ArrayList<>();
        OrderConfirmRE orderConfirmRE = new OrderConfirmRE();
        MemberInfoVO memberInfoVO = new MemberInfoVO();
        memberInfoVO.setOrderId(orderPayInfoNow.get(0).getOrderId());
        memberInfoVO.setMemberId(orderPayInfoNow.get(0).getMemberId());
        OrdMemberVO ordMemberVO = new OrdMemberVO();
        ordMemberVO.setOrderId(orderPayInfoNow.get(0).getOrderId());
        ordMemberVO.setMemberId(orderPayInfoNow.get(0).getMemberId());
        List<OrderRE> orderList = orderService.getOrderByMemberVO(ordMemberVO);
        if (orderList.size() <= 0) {
            return list;
        }
        MemberVO memberVO = new MemberVO();
        memberVO.setId(memberInfoVO.getMemberId());
        MemberRE memberRE = BaseClient.queryOneMember(memberVO);
        if (memberRE == null) {
            return null;
        }
        if (memberRE.getBalance().compareTo(orderList.get(0).getPayPrice()) > 0) {
            MemberVO member = new MemberVO();
            member.setId(memberInfoVO.getMemberId());
        BigDecimal accountBalances = BaseClient.queryOneMember(member).getBalance();
        if (accountBalances.compareTo(orderList.get(0).getPayPrice()) > 0) {
            //加入同步 防止并发提交确认支付信息
            synchronized (this) {
                //更新订单状态、减去用户余额
                try {
                    orderConfirmRE.setStatus(OrderEnum.WAITSHIP.getKey());
                    OrdOrderVO ordOrderVo = new OrdOrderVO();
                    ordOrderVo.setOrderNum(orderList.get(0).getOrderNum());
                    ordOrderVo.setStatus(OrderEnum.WAITSHIP.getKey().longValue());
                    ordOrderVo.setMemberId(memberInfoVO.getMemberId());
                    orderConfirmRE.setShowStatus("成功购买商品");
                    if (orderService.updateOrder(ordOrderVo) > 0) {
                        //减去用户余额
                        MemberVO memberBalanceVO = new MemberVO();
                        memberBalanceVO.setId(memberInfoVO.getMemberId());
                        memberBalanceVO.setBalance((orderList.get(0).getPayPrice()).multiply(new BigDecimal(-1)));
                        if (BaseClient.updateMember(memberBalanceVO) > 0) {
                            list.add(orderConfirmRE);
                        } else {
                            return list;
                        }
                    } else {
                        return list;
                    }
                } catch (Exception e) {
                    LOGGER.error(e.getMessage());
                }
            }
            }
            //加成长值，积分
            MqVO mqVO1 = new MqVO();
            mqVO1.setMemberId(memberInfoVO.getMemberId());
            memberInfoVO.setTotalPrice(orderList.get(0).getPayPrice());
            mqVO1.setGrowthType(GrowthEnum.PURCHASE.getGrowthType());
            mqVO1.setIntegralType(IntegralEnum.PURCHASE.getIntegralType());
            mqVO1.setPurchaseValue(memberInfoVO.getTotalPrice());

            //订单短信
            mqVO1.setGenerateSmsVO(mqVO.getGenerateSmsVO());

            MetaQUtils.sendMsgNoException(new MqProducer(mqVO1));
            //更新订单状态
            orderConfirmRE.setStatus(OrderEnum.WAITSHIP.getKey());
            OrdOrderVO ordOrderVo = new OrdOrderVO();
            ordOrderVo.setOrderNum(orderList.get(0).getOrderNum());
            ordOrderVo.setStatus(OrderEnum.WAITSHIP.getKey().longValue());
            ordOrderVo.setMemberId(memberInfoVO.getMemberId());
            orderConfirmRE.setShowStatus("成功购买商品");
            if (orderService.updateOrder(ordOrderVo) > 0) {
                list.add(orderConfirmRE);
            }

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
        return BaseClient.getMemberDetailById(memberId);
    }

    @Override
    public Integer updateMemberInfo(MemberVO member) {
        return BaseClient.updateMember(member);
    }

    @Override
    public List<MemberRE> getMemberList(MemberListVO memberListVO) {
        return BaseClient.queryMemberList(memberListVO);
    }

    @Override
    public Long queryMemberCount(MemberListVO memberListVO) {
        return BaseClient.queryMemberCount(memberListVO);
    }

    @Override
    public void memberLogin(LoginVO loginLog, MqVO mqVO) {
        BaseClient.insertLoginLog(loginLog);
        //判断是否第一次登陆
        Long loginNum = BaseClient.queryLoginCount(loginLog);
        if (loginNum != null && loginNum == 1) {
            MetaQUtils.sendMsgNoException(new MqProducer(mqVO));
        }
    }

    @Override
    public Integer memberRecharge(MemberVO member, MqVO mqVO) {
        Integer status = BaseClient.updateMember(member);
        mqVO.setRechargeStatus(status);
        mqVO.getMemberRechargeHistory().setStatus(status);
        mqVO.getGenerateSmsVO().setRechargeStatus(status);
        MetaQUtils.sendMsgNoException(new MqProducer(mqVO));
        return status;
    }

}
