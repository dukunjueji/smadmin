package com.ucar.smadmin.base.bd.service.impl;

import com.codingapi.tx.annotation.TxTransaction;
import com.ucar.smadmin.base.bd.service.MemberService;
import com.ucar.smadmin.base.bd.vo.LoginVO;
import com.ucar.smadmin.base.bd.vo.MemberInfoVO;
import com.ucar.smadmin.base.bd.vo.MemberListVO;
import com.ucar.smadmin.base.bd.vo.MemberVO;
import com.ucar.smadmin.common.mq.MqService;
import com.ucar.smadmin.common.redis.RedissonManager;
import com.ucar.smadmin.common.utils.EncryptUtil;
import com.ucar.smadmin.enums.GoodsStatusEnum;
import com.ucar.smadmin.enums.GrowthEnum;
import com.ucar.smadmin.enums.IntegralEnum;
import com.ucar.smadmin.enums.OrderEnum;
import com.ucar.smadmin.gds.service.GoodsService;
import com.ucar.smadmin.gds.vo.GoodsStokeVO;
import com.ucar.smadmin.ord.service.OrderService;
import com.ucar.smadmin.ord.vo.OrdMemberVO;
import com.ucar.smadmin.ord.vo.OrdOrderVO;
import com.ucar.smadmin.remote.client.OrderClient;
import com.ucar.smadmin.remote.remoteclient.BaseClient;
import com.ucar.smapi.base.bd.dto.LoginLogDTO;
import com.ucar.smapi.base.bd.dto.MemberDTO;
import com.ucar.smapi.base.bd.re.MemberDetailRE;
import com.ucar.smapi.base.bd.re.MemberRE;
import com.ucar.smapi.common.mq.MqVO;
import com.ucar.smapi.gds.dto.GoodsAndPropertyDTO;
import com.ucar.smapi.gds.re.GoodsStokeRE;
import com.ucar.smapi.ord.re.OrderConfirmRE;
import com.ucar.smapi.ord.re.OrderGoodsRE;
import com.ucar.smapi.ord.re.OrderRE;
import org.apache.commons.collections.CollectionUtils;
import org.redisson.api.RLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private MqService mqService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private BaseClient baseClient;
    @Autowired
    private OrderClient orderClient;

    @Override
    public Long insertMember(MemberVO memberVO) {
        MemberDTO memberDTO = new MemberDTO();
        BeanUtils.copyProperties(memberVO, memberDTO);
        return baseClient.insertMember(memberDTO).getRe();
    }

    @Override
    public MemberRE queryOneMember(MemberVO member) {
        if (member.getPassword() != null) {
            //密码加密
            member.setPassword(EncryptUtil.md5(member.getPassword()));
        }
        MemberDTO memberDTO = new MemberDTO();
        BeanUtils.copyProperties(member, memberDTO);
        return baseClient.queryOneMember(memberDTO).getRe();
    }

    @Override
    public Integer updateMember(MemberVO member) {
        member.setPassword(EncryptUtil.md5(member.getPassword()));
        MemberDTO memberDTO = new MemberDTO();
        BeanUtils.copyProperties(member, memberDTO);
        return baseClient.updateMember(memberDTO).getRe();
    }

    /**
     * 判断该商品是否下架、删除、无库存
     */
    private List<OrderConfirmRE> validationGoodsStatus(List<OrderGoodsRE> orderGdsList) {
        List<OrderConfirmRE> list = new ArrayList<>();
        OrderConfirmRE orderConfirmRE = new OrderConfirmRE();
        GoodsStokeVO goodsStokeVO;
        for (int i = 0; i < orderGdsList.size(); i++) {
            //更新库存表、插入用户订单表和订单商品信息表、删除购物车商品信息,判断商品是否删除或者下架和库存是否足够
            goodsStokeVO = new GoodsStokeVO();
            goodsStokeVO.setPropertyId(orderGdsList.get(i).getGoodsPropertyId());
            goodsStokeVO.setStock((long) orderGdsList.get(i).getGoodsNum());
            GoodsStokeRE goodsStokeRE = goodsService.selectGoodsStatus(goodsStokeVO);
            if (goodsStokeRE.getIsDelete().equals(GoodsStatusEnum.GOODS_DELETE.getType())) {
                StringBuilder temp = new StringBuilder();
                temp.append("您的商品：" + goodsStokeRE.getGoodsName() + "\n" + "规格:");
                temp.append(goodsStokeRE.getGoodsProperty() + "已经被删除了，点击返回订单");
                orderConfirmRE.setStatus(OrderEnum.NOORDER.getKey());
                orderConfirmRE.setShowStatus(temp.toString());
                list.add(orderConfirmRE);
                return list;
            }
            if (goodsStokeRE.getStatus().equals(GoodsStatusEnum.GOODS_IS_SHELVES.getType())) {
                StringBuilder temp = new StringBuilder();
                temp.append("您的商品：" + goodsStokeRE.getGoodsName() + "\n" + "规格:");
                temp.append(goodsStokeRE.getGoodsProperty() + "已经被下架了，点击返回订单");
                orderConfirmRE.setStatus(OrderEnum.NOORDER.getKey());
                orderConfirmRE.setShowStatus(temp.toString());
                list.add(orderConfirmRE);
                return list;
            }
        }
        orderConfirmRE.setStatus(OrderEnum.WAITPAY.getKey());
        list.add(orderConfirmRE);
        return list;
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
        //获取订单信息
        List<OrderRE> orderList = orderService.getOrderByMemberVO(ordMemberVO);
        //获取订单商品信息和判断商品状态
        List<OrderGoodsRE> orderGdsList = orderClient.getOrderGoodsByOrderId(orderPayInfoNow.get(0).getOrderId().intValue());
        if (orderList.size() <= 0) {
            return list;
        }
        List<OrderConfirmRE> goodsStatusList = validationGoodsStatus(orderGdsList);
        if (CollectionUtils.isEmpty(goodsStatusList)) {
            return goodsStatusList;
        } else if (goodsStatusList.get(0).getStatus() != (int) OrderEnum.WAITPAY.getKey().longValue()) {
            return goodsStatusList;
        }
        MemberVO memberVO = new MemberVO();
        memberVO.setId(memberInfoVO.getMemberId());
        MemberDTO memberDTO = new MemberDTO();
        BeanUtils.copyProperties(memberVO, memberDTO);
        MemberRE memberRE = baseClient.queryOneMember(memberDTO).getRe();
        if (memberRE == null) {
            return null;
        }
        if (memberRE.getBalance().compareTo(orderList.get(0).getPayPrice()) >= 0) {
            //加入分布式锁 防止并发提交确认支付信息，以订单编号加锁
            RLock lock = RedissonManager.getInstance().getLock(orderList.get(0).getOrderNum(), true);
            //更新订单状态、减去用户余额
            try {
                lock.lock(RedissonManager.DEFAULT_EXPIRED_TIME, RedissonManager.DEFAULT_TIME_UNIT);
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
                    BeanUtils.copyProperties(memberBalanceVO, memberDTO);
                    if (baseClient.updateMember(memberDTO).getRe() > 0) {
                        list.add(orderConfirmRE);
                    } else {
                        return list;
                    }
                } else {
                    return list;
                }
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
                return list;
            } finally {
                lock.unlock();
            }
            // 加上对应的商品销量
            for (int i = 1, j = orderPayInfoNow.size(); i < j; i++) {
                GoodsAndPropertyDTO goodsStockVO = new GoodsAndPropertyDTO();
                goodsStockVO.setStock(orderPayInfoNow.get(i).getGoodsNum());
                goodsStockVO.setGoodsId(orderPayInfoNow.get(i).getGoodsId());
                goodsService.updateSales(goodsStockVO);
            }
            // 加成长值，积分
            MqVO mqVO1 = new MqVO();
            mqVO1.setMemberId(memberInfoVO.getMemberId());
            memberInfoVO.setTotalPrice(orderList.get(0).getPayPrice());
            mqVO1.setGrowthType(GrowthEnum.PURCHASE.getGrowthType());
            mqVO1.setIntegralType(IntegralEnum.PURCHASE.getIntegralType());
            mqVO1.setPurchaseValue(memberInfoVO.getTotalPrice());
            //订单短信
            mqVO.getGenerateSmsVO().setEmil(memberRE.getEmail());
            mqVO1.setGenerateSmsVO(mqVO.getGenerateSmsVO());
            mqService.sendSms(mqVO);
            mqService.sendMessage(mqVO1);
            mqService.sendGrowth(mqVO1);
            mqService.sendIntegral(mqVO1);
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
        return baseClient.getMemberDetailById(memberId).getRe();
    }

    /**
     * 测试分布式事务
     * @param member
     * @return
     */
    @Override
    @TxTransaction(isStart=true)
    @Transactional
    public Integer updateMemberInfo(MemberVO member) {
        MemberDTO memberDTO = new MemberDTO();
        BeanUtils.copyProperties(member, memberDTO);
        return baseClient.updateMember(memberDTO).getRe();
    }

    @Override
    public List<MemberRE> getMemberList(MemberListVO memberListVO) {
        MemberDTO memberDTO = new MemberDTO();
        BeanUtils.copyProperties(memberListVO, memberDTO);
        return baseClient.queryMemberList(memberDTO).getRe();
    }

    @Override
    public Long queryMemberCount(MemberListVO memberListVO) {
        MemberDTO memberDTO = new MemberDTO();
        BeanUtils.copyProperties(memberListVO, memberDTO);
        return baseClient.queryMemberCount(memberDTO).getRe();
    }

    @Override
    public void memberLogin(LoginVO loginLog, MqVO mqVO) {
        LoginLogDTO loginLogDTO = new LoginLogDTO();
        loginLogDTO.setIp(loginLog.getIp());
        loginLogDTO.setMemberId(loginLog.getMemberId());
        baseClient.insertLoginLog(loginLogDTO).getRe();
        //判断是否第一次登陆
        loginLogDTO.setMemberId(loginLog.getMemberId());
        Long loginNum = baseClient.queryLoginCount(loginLogDTO).getRe();
        if (loginNum != null && loginNum == 1) {
            mqService.sendGrowth(mqVO);
        }
    }

    @Override
    public Integer memberRecharge(MemberVO member, MqVO mqVO) {
        MemberDTO memberDTO = new MemberDTO();
        BeanUtils.copyProperties(member, memberDTO);
        Integer status = baseClient.updateMember(memberDTO).getRe();
        mqVO.setRechargeStatus(status);
        mqVO.getMemberRechargeHistory().setStatus(status);
        mqVO.getGenerateSmsVO().setRechargeStatus(status);
        mqService.sendRecharge(mqVO);
        mqService.sendMessage(mqVO);
        mqService.sendSms(mqVO);
        return status;
    }

}
