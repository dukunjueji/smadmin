package com.uc.training.smadmin.bd.service.impl;

import com.uc.training.common.enums.OrderEnum;
import com.uc.training.smadmin.bd.dao.ApiMemberDao;
import com.uc.training.smadmin.bd.model.Member;
import com.uc.training.smadmin.bd.re.MemberDetailRE;
import com.uc.training.smadmin.bd.re.MemberInfoRE;
import com.uc.training.smadmin.bd.service.ApiMemberService;
import com.uc.training.smadmin.bd.vo.MemberInfoVO;
import com.uc.training.smadmin.bd.vo.MemberLoginVO;
import com.uc.training.smadmin.gds.dao.GoodsDao;
import com.uc.training.smadmin.gds.vo.GoodsStokeVO;
import com.uc.training.smadmin.ord.dao.OrderDao;
import com.uc.training.smadmin.ord.re.OrderConfirmRE;
import com.uc.training.smadmin.ord.vo.OrdOrderVo;
import com.uc.training.smadmin.utils.EncryptUtil;
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
public class ApiMemberServiceImpl implements ApiMemberService {

    @Autowired
    private ApiMemberDao apiMemberDao;

    @Autowired
    private GoodsDao goodsDao;

    @Autowired
    private OrderDao orderDao;

    @Override
    public void insertMember(Member member) {
        String encryptPaswd = EncryptUtil.md5(member.getPassword());
        member.setPassword(encryptPaswd);
        apiMemberDao.insertMember(member);
    }

    @Override
    public Member queryOneMember(Member member) {
        if (member.getPassword() != null){
            //密码加密
            member.setPassword(EncryptUtil.md5(member.getPassword()));
        }
        return apiMemberDao.queryOneMember(member);
    }

    @Override
    public void updateMember(Member member) {
        String newPaswd = EncryptUtil.md5(member.getPassword());
        member.setPassword(newPaswd);
        apiMemberDao.updateMember(member);
    }

    @Override
    public void updateMemberBalance(Member member) {
        apiMemberDao.updateMemberBalance(member);
    }

    /**
     * 查询余额
     * @param orderPayInfoNow
     */
    @Override
    public List<OrderConfirmRE> queryBalances(List<MemberInfoVO> orderPayInfoNow) {
        List<OrderConfirmRE> list = new ArrayList<>();
        OrderConfirmRE orderConfirmRE =new OrderConfirmRE();
        MemberInfoVO memberInfoVO = new MemberInfoVO();
        memberInfoVO.setTotalPrice(orderPayInfoNow.get(0).getTotalPrice());
        memberInfoVO.setOrderName(orderPayInfoNow.get(0).getOrderName());
        memberInfoVO.setMemberId(1L);
        Double accountBalances = apiMemberDao.queryBalances(memberInfoVO.getMemberId());
        if ( accountBalances > memberInfoVO.getTotalPrice()){
            // 加上对应的商品销量
            for (int i = 1, j = orderPayInfoNow.size();i < j; i++){
                GoodsStokeVO goodsStokeVO = new GoodsStokeVO();
                goodsStokeVO.setStoke(orderPayInfoNow.get(i).getGoodsNum());
                goodsStokeVO.setGoodsId(orderPayInfoNow.get(i).getGoodsId());
                goodsDao.updateSales(goodsStokeVO);
            }
            //加成长值，积分

            //更新订单状态
            orderConfirmRE.setStatus(OrderEnum.WAITSHIP.getKey());
            OrdOrderVo ordOrderVo = new OrdOrderVo();
            ordOrderVo.setOrderNum(orderPayInfoNow.get(0).getOrderName());
            ordOrderVo.setStatus(OrderEnum.WAITSHIP.getKey().longValue());
            orderConfirmRE.setShowStatus("成功购买商品");
            orderDao.updateOrder(ordOrderVo);
            //减去用户余额
            list.add(orderConfirmRE);
            return list;
        }else {
            orderConfirmRE.setShowStatus("余额不足，请充值或者返回购物车重新选取商品");
            list.add(orderConfirmRE);
            orderConfirmRE.setStatus(OrderEnum.WAITPAY.getKey());
            return list;
        }
    }





    @Override
    public MemberDetailRE getMemberDetailById(Long memberId) {
        return apiMemberDao.getMemberDetailById(memberId);
    }

    @Override
    public Member getMemberLogin(MemberLoginVO memberLoginVO) {
        return apiMemberDao.getMemberLogin(memberLoginVO);
    }

    @Override
    public int updateMemberInfo(Member member) {
        return this.apiMemberDao.updateMemberInfo(member);
    }

    @Override
    public MemberInfoRE queryOneMemberById(Long uid) {
        return apiMemberDao.queryOneMemberById(uid);
    }

}
