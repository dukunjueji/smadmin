package com.uc.training.smadmin.ord.service.impl;

import com.uc.training.common.enums.GoodsStatusEnum;
import com.uc.training.common.enums.OrderEnum;
import com.uc.training.common.enums.UUIDTypeEnum;
import com.uc.training.smadmin.gds.dao.GoodsDao;
import com.uc.training.smadmin.gds.re.GoodsDetailRE;
import com.uc.training.smadmin.gds.re.GoodsStokeRE;
import com.uc.training.smadmin.gds.re.PropertyUrlRE;
import com.uc.training.smadmin.gds.service.GoodsService;
import com.uc.training.smadmin.gds.vo.GoodsStokeVO;
import com.uc.training.smadmin.ord.dao.CartGoodsDao;
import com.uc.training.smadmin.ord.dao.OrderDao;
import com.uc.training.smadmin.ord.dao.OrderGoodsDao;
import com.uc.training.smadmin.ord.model.CartGoods;
import com.uc.training.smadmin.ord.model.OrdOrder;
import com.uc.training.smadmin.ord.model.Order;
import com.uc.training.smadmin.ord.model.OrderGoods;
import com.uc.training.smadmin.ord.re.*;
import com.uc.training.smadmin.ord.service.OrderService;
import com.uc.training.smadmin.ord.vo.OrdCartGoodsVo;
import com.uc.training.smadmin.ord.vo.OrdMemberVO;
import com.uc.training.smadmin.ord.vo.OrdOrderGoodsVo;
import com.uc.training.smadmin.ord.vo.OrdOrderVo;
import com.uc.training.smadmin.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单服务实现
 *
 * @author DK
 * @date 2018/10/19
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    CartGoodsDao cartGoodsDao;

    @Autowired
    OrderDao orderDao;

    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderGoodsDao orderGoodsDao;

    @Override
    public List<CartGoods> getCarGoodsById(Long memberId) {
        List<CartGoods> cartList = cartGoodsDao.getCartGoodsById(memberId);
        if (cartList.size() <= 0) {
            return null;
        }
        return cartList;
    }

    /**
     * 获取订单商品列表
     *
     * @param orderGodsList
     * @return
     */
    @Override
    public List<OrdOrderGoodsVo> getOrderGoodsById(List<OrdOrderGoodsVo> orderGodsList) {
        List<OrdOrderGoodsVo> list = new ArrayList<>();
        OrdOrderGoodsVo ordOrderGoodsVo;
        for (int i = 0; i < orderGodsList.size(); i++) {
            ordOrderGoodsVo = new OrdOrderGoodsVo();
            GoodsDetailRE gdDTO = goodsService.getGoodsDetailByPropertyId(orderGodsList.get(i).getPropertyId());
            if (gdDTO == null) {
                return list;
            }
            ordOrderGoodsVo.setGoodsId(orderGodsList.get(i).getGoodsId());
            ordOrderGoodsVo.setGdsName(gdDTO.getName());
            ordOrderGoodsVo.setGdsUrl(gdDTO.getPicUrl().get(0).getPicUrl());
            ordOrderGoodsVo.setPropertyId(orderGodsList.get(i).getPropertyId());
            ordOrderGoodsVo.setProperty(gdDTO.getProperty());
            ordOrderGoodsVo.setSalePrice(gdDTO.getSalePrice());
            ordOrderGoodsVo.setDiscountPrice(gdDTO.getDiscountPrice());
            ordOrderGoodsVo.setStatus(gdDTO.getStatus());
            ordOrderGoodsVo.setIsDiscount(gdDTO.getIsDiscount());
            list.add(ordOrderGoodsVo);
        }
        return list;
    }

    @Override
    public void addCarGoods(OrdCartGoodsVo ordCartGoodsVo) {
        cartGoodsDao.insertCartGoods(ordCartGoodsVo);
    }

    @Override
    public int deleteCarGoods(OrdCartGoodsVo ordCartGoodsVo) {
        return cartGoodsDao.deleteCartGoods(ordCartGoodsVo);
    }

    @Override
    public int updataCarGoodsNum(OrdCartGoodsVo ordCartGoodsVo) {
        return cartGoodsDao.updataCartGoodsNum(ordCartGoodsVo);
    }

    @Override
    public Integer queryOrderCount(Long memberId) {
        return this.orderDao.queryOrderCount(memberId);
    }

    /**
     * 提交订单验证信息
     */
    @Override
    public List<OrderConfirmRE> confirmOrderInfo(List<OrdOrderGoodsVo> orderInfoListNow) {
        OrderConfirmRE orderConfirmRE = new OrderConfirmRE();
        List<OrderConfirmRE> list = new ArrayList<>();
        int a = 2;
        GoodsStokeVO goodsStokeVO;
        for (int i = 0; i < orderInfoListNow.size() - a; i++) {
            //更新库存表、插入用户订单表和订单商品信息表、删除购物车商品信息,判断商品是否删除或者下架和库存是否足够
            goodsStokeVO = new GoodsStokeVO();
            goodsStokeVO.setPropertyId(orderInfoListNow.get(i).getPropertyId());
            goodsStokeVO.setStoke((long) orderInfoListNow.get(i).getNum());
            GoodsStokeRE goodsStokeRE = goodsService.selectGoodsStatus(goodsStokeVO);
            if (goodsStokeRE.getIsDelete() == GoodsStatusEnum.GoodsIsDelete.getType()) {
                StringBuilder temp = new StringBuilder();
                temp.append("您的商品：" + goodsStokeRE.getGoodsName() + "\n" + "规格:");
                temp.append(goodsStokeRE.getGoodsProperty() + "已经被删除了，点击返回购物车，再重新选择");
                orderConfirmRE.setShowStatus(temp.toString());
                orderConfirmRE.setGoodsStatus(OrderEnum.NOORDER.getKey());
                list.add(orderConfirmRE);
                return list;
            }
            if (goodsStokeRE.getStatus() == GoodsStatusEnum.GoodsIsShelves.getType()) {
                StringBuilder temp = new StringBuilder();
                temp.append("您的商品：" + goodsStokeRE.getGoodsName() + "\n" + "规格:");
                temp.append(goodsStokeRE.getGoodsProperty() + "已经被下架了，点击返回购物车，再重新选择");
                orderConfirmRE.setShowStatus(temp.toString());
                orderConfirmRE.setGoodsStatus(OrderEnum.NOORDER.getKey());
                list.add(orderConfirmRE);
                return list;
            }
            if (goodsStokeRE.getStoke() < orderInfoListNow.get(i).getNum()) {
                StringBuilder temp = new StringBuilder();
                temp.append("您的商品：" + goodsStokeRE.getGoodsName() + "\n" + "规格:");
                temp.append(goodsStokeRE.getGoodsProperty() + "已经被卖完了，点击返回购物车，再重新选择");
                orderConfirmRE.setShowStatus(temp.toString());
                orderConfirmRE.setGoodsStatus(OrderEnum.NOORDER.getKey());
                list.add(orderConfirmRE);
                return list;
            }
        }
        //插入用户订单表
        Order order = new Order();
        order.setMemberId(orderInfoListNow.get(orderInfoListNow.size() - 2).getMemberId());
        order.setOrderPrice(orderInfoListNow.get(orderInfoListNow.size() - 1).getTotalPrice());
        order.setPayPrice(orderInfoListNow.get(orderInfoListNow.size() - 1).getTotalPrice());
        order.setReceiptAddress(orderInfoListNow.get(orderInfoListNow.size() - 2).getReceiptAddress());
        order.setReceiptName(orderInfoListNow.get(orderInfoListNow.size() - 2).getReceiptName());
        order.setReceiptTel(orderInfoListNow.get(orderInfoListNow.size() - 2).getReceiptTel());
        order.setStatus(OrderEnum.WAITPAY.getKey());
        order.setIsDelete(0);
        //生成订单编号
        order.setOrderNum(UUIDUtil.getUuidByType(UUIDTypeEnum.ORDERID.getType()));
        Long oderId = orderDao.insertOrder(order);
        //遍历orderInfoListNow
        OrderGoods orderGoods;
        OrdCartGoodsVo ordCartGoodsVo;
        for (int i = 0; i < orderInfoListNow.size() - a; i++) {
            //插入订单商品信息表
            orderGoods = new OrderGoods();
            goodsStokeVO = new GoodsStokeVO();
            orderGoods.setOrderId(oderId);
            orderGoods.setGoodsId(orderInfoListNow.get(i).getGoodsId());
            orderGoods.setPayPrice(orderInfoListNow.get(i).getSalePrice());
            orderGoods.setGoodsPropertyId(orderInfoListNow.get(i).getPropertyId());
            orderGoods.setGoodsNum(orderInfoListNow.get(i).getNum());
            orderGoods.setSalePrice(orderInfoListNow.get(i).getSalePrice());
            orderGoods.setDiscountPrice(0);
            orderGoods.setGoodsPropertyId(orderInfoListNow.get(i).getPropertyId());
            orderGoodsDao.insertOrderGoods(orderGoods);
            //更新商品对应的库存
            goodsStokeVO.setStoke(orderGoods.getGoodsNum().longValue());
            goodsStokeVO.setPropertyId(orderGoods.getGoodsPropertyId());
            goodsService.updateAndDeductStoke(goodsStokeVO);
            //删除购物车信息表
            ordCartGoodsVo = new OrdCartGoodsVo();
            ordCartGoodsVo.setPropertyId(orderInfoListNow.get(i).getPropertyId());
            ordCartGoodsVo.setMemberId(orderInfoListNow.get(orderInfoListNow.size() - 2).getMemberId());
            cartGoodsDao.deleteCartGoods(ordCartGoodsVo);
        }
        orderConfirmRE.setShowStatus("已成功生成订单");
        orderConfirmRE.setGoodsStatus((int) OrderEnum.WAITPAY.getKey().longValue());
        orderConfirmRE.setOrderNum(order.getOrderNum());
        list.add(orderConfirmRE);
        return list;
    }

  @Override
  public List<OrderRe> getOrderPage(OrdOrderVo orderVo) {
    List<OrderRe> list = orderDao.getOrderPage(orderVo);
    if (list.size() <= 0) {
      return null;
    }
/*    for (OrderRe orderRe : list) {
      OrderEnum orderEnum;
      orderEnum = OrderEnum.getEnumByKey(orderRe.getStatus());
      if (orderEnum != null) {
        orderRe.setShowStatus(orderEnum.getValue());
      }
    }*/
    return list;
  }

    @Override
    public Integer getOrderTotal(OrdOrderVo orderVo) {
        return orderDao.getOrderTotal(orderVo);
    }

  @Override
  public List<OrderStatusRe> getOrderEnum() {
    List<OrderStatusRe> list = new ArrayList<OrderStatusRe>();
    OrderEnum orderEnum;
    int max = OrderEnum.getMaxKey();
    int i = 0;
    do {
      OrderStatusRe orderStatusRe = new OrderStatusRe();
      orderEnum = OrderEnum.getEnumByKey(i);
      if (orderEnum != null) {
        orderStatusRe.setValue(i);
        orderStatusRe.setLabel(orderEnum.getValue());
        list.add(orderStatusRe);
      }
      i++;
    } while (max >= i);
    OrderStatusRe orderStatusRe = new OrderStatusRe();
    orderStatusRe.setLabel("全部");
    list.add(orderStatusRe);
    return list;
  }

  @Override
  public int logicDelOrder(List<Long> list) {
    return orderDao.logicDelOrder(list);
  }

  @Override
  public int updateOrder(OrdOrderVo ordOrderVo) {
    return orderDao.updateOrder(ordOrderVo);
  }


    @Override
    public List<OrderGoodsDetailRe> getOrderGdsById(Integer id) {
        List<OrderGoods> orderGdslist;
        List<OrderGoodsDetailRe> list = new ArrayList<>();
        orderGdslist = orderGoodsDao.getOrderGoodsByOrderId(id);
        if (CollectionUtils.isEmpty(orderGdslist)) {
            return null;
        }
        OrderGoodsDetailRe orderGoodsDetailRe;
        GoodsDetailRE gdDTO;
        for (OrderGoods orderGoods : orderGdslist) {
            orderGoodsDetailRe = new OrderGoodsDetailRe();
            gdDTO = goodsService.getGoodsDetailByPropertyId(orderGoods.getGoodsPropertyId());
            orderGoodsDetailRe.setGoodsName(gdDTO.getName());
            orderGoodsDetailRe.setGoodsNum(orderGoods.getGoodsNum());
            orderGoodsDetailRe.setGoodsProperty(gdDTO.getProperty());
            orderGoodsDetailRe.setGoodsPrice(gdDTO.getSalePrice());
            orderGoodsDetailRe.setGoodsUrl(gdDTO.getPicUrl().get(0).getPicUrl());
            list.add(orderGoodsDetailRe);
        }
        return list;
    }

    /**
     * 获取订单商品信息
     * @param id
     * @return
     */
    public List<OrderGoodsDetailRe> getOrderGdsByIds(Integer id) {
        List<OrderGoods> orderGdslist;
        // TODO: 2018/10/31 待改进--查询商品属性语句 
        List<OrderGoodsDetailRe> list = new ArrayList<>();
        orderGdslist = orderGoodsDao.getOrderGoodsByOrderId(id);
        if (CollectionUtils.isEmpty(orderGdslist)) {
            return null;
        }
        OrderGoodsDetailRe orderGoodsDetailRe;
        GoodsDetailRE gdDTO;
        Long time1=System.currentTimeMillis()/1000;
        List<Long> propertyIds =new ArrayList<>();
        for (int i = 0; i < orderGdslist.size(); i++) {
            propertyIds.add(orderGdslist.get(i).getGoodsPropertyId());
        }
        List<GoodsDetailRE> goodsDetailRES=goodsService.getGoodsDetailByPropertyIds(propertyIds);
        for (int i = 0; i < orderGdslist.size(); i++) {
            orderGoodsDetailRe = new OrderGoodsDetailRe();
            //gdDTO = goodsService.getGoodsDetailByPropertyId(orderGoods.getGoodsPropertyId());
            orderGoodsDetailRe.setGoodsName(goodsDetailRES.get(i).getName());
            orderGoodsDetailRe.setGoodsNum(orderGdslist.get(i).getGoodsNum());
            orderGoodsDetailRe.setGoodsProperty(goodsDetailRES.get(i).getProperty());
            orderGoodsDetailRe.setGoodsPrice(goodsDetailRES.get(i).getSalePrice());
            orderGoodsDetailRe.setGoodsUrl(goodsDetailRES.get(i).getPicUrl().get(0).getPicUrl());
            list.add(orderGoodsDetailRe);
        }
        Long time2=System.currentTimeMillis()/1000;
        System.out.println("------time1-----"+(time2-time1));
        return list;
    }

    /**
     * 根据会员id获取订单信息
     * @param ordMemberVO
     * @return
     */
    @Override
    public List<OrderInfoRE> getOrderInfoListByMemberId(OrdMemberVO ordMemberVO) {
        //先获该用户的取订单id，然后查询每条订单的状态，订单的金额 以及获取订单的商品信息
        List<OrderInfoRE> orderInfoREList = new ArrayList<>();
        List<Order> orderList = orderDao.getOrderById(ordMemberVO);
        if (CollectionUtils.isEmpty(orderList)) {
            return orderInfoREList;
        }
        OrderInfoRE orderInfoRE;
        for (Order order : orderList) {
            orderInfoRE = new OrderInfoRE();
            orderInfoRE.setOrderNum(order.getOrderNum());
            orderInfoRE.setStatus(order.getStatus().longValue());
            orderInfoRE.setPayPrice(order.getPayPrice());
            orderInfoRE.setOrderPrice(order.getOrderPrice());
            List<OrderGoodsDetailRe> orderGodsList = getOrderGdsById(order.getId().intValue());
            orderInfoRE.setOrderGoodsDetailRe(orderGodsList);
            orderInfoREList.add(orderInfoRE);
        }
        return orderInfoREList;
    }
}

