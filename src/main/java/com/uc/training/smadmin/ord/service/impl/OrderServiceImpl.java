package com.uc.training.smadmin.ord.service.impl;

import com.uc.training.common.enums.OrderEnum;
import com.sun.corba.se.spi.orb.ORBData;
import com.uc.training.common.enums.GoodsStatusEnum;
import com.uc.training.common.enums.UUIDTypeEnum;
import com.uc.training.smadmin.gds.dao.GoodsDao;
import com.uc.training.smadmin.gds.re.GoodsDetailRE;
import com.uc.training.smadmin.gds.re.GoodsRE;
import com.uc.training.smadmin.gds.re.GoodsStokeRE;
import com.uc.training.smadmin.gds.vo.GoodsStokeVO;
import com.uc.training.smadmin.ord.dao.CartGoodsDao;
import com.uc.training.smadmin.ord.dao.OrderDao;
import com.uc.training.smadmin.ord.dao.OrderGoodsDao;
import com.uc.training.smadmin.ord.model.CartGoods;
import com.uc.training.smadmin.ord.model.Order;
import com.uc.training.smadmin.ord.model.OrderGoods;
import com.uc.training.smadmin.ord.re.OrderRe;
import com.uc.training.smadmin.ord.re.OrderStatusRe;
import com.uc.training.smadmin.ord.service.OrderService;
import com.uc.training.smadmin.ord.vo.OrdCartGoodsVo;
import com.uc.training.smadmin.ord.vo.OrdOrderGoodsVo;
import com.uc.training.smadmin.ord.vo.OrdOrderVo;
import com.uc.training.smadmin.utils.UUIDUtil;
import com.ycc.base.common.Result;
import org.apache.poi.util.SystemOutLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单服务实现
 * @author DK
 * @date 2018/10/19
 */
@Service
public class OrderServiceImpl implements OrderService {
  @Autowired
  CartGoodsDao cartGoodsDao;

  @Autowired
  GoodsDao goodsDao;

  @Autowired
  OrderDao orderDao;

  @Autowired
  OrderGoodsDao orderGoodsDao;

  @Override
  public List<OrdCartGoodsVo> getCarGoodsById(Long memberId) {
    List<CartGoods> cartList;
    List<OrdCartGoodsVo> list = new ArrayList<>();
    cartList = cartGoodsDao.getCartGoodsById(memberId);
    if(cartList.size() <= 0){
      return null;
    }
    for (CartGoods cartGoods :cartList) {
      OrdCartGoodsVo ordCartgoodsVo = new OrdCartGoodsVo();
      GoodsDetailRE gdDTO ;
      gdDTO = goodsDao.getGoodsDetailByPropertyId(cartGoods.getGoodsPropertyId());
      ordCartgoodsVo.setCartId(cartGoods.getId());
      ordCartgoodsVo.setGoodsId(cartGoods.getGoodsId());
      ordCartgoodsVo.setGdsName(gdDTO.getName());
      ordCartgoodsVo.setGdsUrl(gdDTO.getPicUrl());
      ordCartgoodsVo.setPropertyId(cartGoods.getGoodsPropertyId());
      ordCartgoodsVo.setProperty(gdDTO.getProperty());
      ordCartgoodsVo.setSalePrice(gdDTO.getSalePrice());
      ordCartgoodsVo.setDiscountPrice(gdDTO.getDiscountPrice());
      ordCartgoodsVo.setStatus(gdDTO.getStatus());
      ordCartgoodsVo.setIsDiscount(gdDTO.getIsDiscount());
      ordCartgoodsVo.setNum(cartGoods.getGoodsNum());
      ordCartgoodsVo.setStock(gdDTO.getStock());
      list.add(ordCartgoodsVo);
    }
    return list;
  }

  @Override
  public List<OrdOrderGoodsVo> getOrderGoodsById(List<OrdOrderGoodsVo> orderGodsList) {
    List<OrdOrderGoodsVo> list = new ArrayList<>();
    //orderList = cartGoodsDao.getCartGoodsById(1L);
    for (int i = 0;i < orderGodsList.size(); i ++) {
      OrdOrderGoodsVo ordOrderGoodsVo = new OrdOrderGoodsVo();
      GoodsDetailRE gdDTO = goodsDao.getGoodsDetailByPropertyId(orderGodsList.get(i).getPropertyId());
      ordOrderGoodsVo.setGoodsId(orderGodsList.get(i).getGoodsId());
      ordOrderGoodsVo.setGdsName(gdDTO.getName());
      ordOrderGoodsVo.setGdsUrl(gdDTO.getPicUrl());
      ordOrderGoodsVo.setPropertyId(orderGodsList.get(i).getPropertyId());
      ordOrderGoodsVo.setProperty(gdDTO.getProperty());
      ordOrderGoodsVo.setSalePrice(gdDTO.getSalePrice());
      ordOrderGoodsVo.setDiscountPrice(gdDTO.getDiscountPrice());
      ordOrderGoodsVo.setStatus(gdDTO.getStatus());
      ordOrderGoodsVo.setIsDiscount(gdDTO.getIsDiscount());
      //ordCartgoodsVo.setNum(cartGoods.getGoodsNum());
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
   *提交订单验证信息
   */
  @Override
  public List<OrderRe> updateOrderInfo(List<OrdOrderGoodsVo> orderInfoListNow) {
    OrderRe orderRe = new OrderRe();
    List<OrderRe> list = new ArrayList<>();
    for (int i = 0; i < orderInfoListNow.size()-2; i++) {
      //更新库存表、插入用户订单表和订单商品信息表、删除购物车商品信息,判断商品是否删除或者下架和库存是否足够
      GoodsStokeVO goodsStokeVO = new GoodsStokeVO();
      goodsStokeVO.setPropertyId(orderInfoListNow.get(i).getPropertyId());
      GoodsStokeRE goodsStokeRE = goodsDao.selectGoodsStatus(goodsStokeVO);
      if (goodsStokeRE.getIsDelete() == GoodsStatusEnum.GoodsIsDelete.getType()){
        StringBuilder temp = new StringBuilder();
        temp.append("您的商品："+goodsStokeRE.getGoodsName()+"\n"+"规格:");
        temp.append(goodsStokeRE.getGoodsProperty()+"已经被删除了，请返回购物车重新选择");
        orderRe.setShowStatus(temp.toString());
        orderRe.setGoodsStatus(0);
        list.add(orderRe);
        return list;
      }
      if (goodsStokeRE.getStatus() == GoodsStatusEnum.GoodsIsShelves.getType()){
        StringBuilder temp = new StringBuilder();
        temp.append("您的商品："+goodsStokeRE.getGoodsName()+"\n"+"规格:");
        temp.append(goodsStokeRE.getGoodsProperty()+"已经被下架了，请返回购物车重新选择");
        orderRe.setShowStatus(temp.toString());
        orderRe.setGoodsStatus(0);
        list.add(orderRe);
        return list;
      }
      if (goodsStokeRE.getStoke() < orderInfoListNow.get(i).getNum()){
        StringBuilder temp = new StringBuilder();
        temp.append("您的商品："+goodsStokeRE.getGoodsName()+"\n"+"规格:");
        temp.append(goodsStokeRE.getGoodsProperty()+"已经被卖完了，请返回购物车重新选择");
        orderRe.setShowStatus(temp.toString());
        orderRe.setGoodsStatus(0);
        list.add(orderRe);
        return list;
      }
    }
    //插入用户订单表
    Order order = new Order();
    order.setMemberId(1L);
    order.setOrderPrice(orderInfoListNow.get(orderInfoListNow.size() - 1).getTotalPrice());
    order.setPayPrice(orderInfoListNow.get(orderInfoListNow.size() - 1).getTotalPrice());
    order.setReceiptAddress(orderInfoListNow.get(orderInfoListNow.size() - 2).getReceiptAddress());
    order.setReceiptName(orderInfoListNow.get(orderInfoListNow.size() - 2).getReceiptName());
    order.setReceiptTel(orderInfoListNow.get(orderInfoListNow.size() - 2).getReceiptTel());
    order.setStatus(1);
    order.setIsDelete(0);
    //生成订单编号
    order.setOrderNum(UUIDUtil.getUuidByType(UUIDTypeEnum.ORDERID.getType()));
    Long oderId = orderDao.insertOrder(order);
    //遍历orderInfoListNow
    for (int i = 0; i < orderInfoListNow.size()-2; i++) {
      //插入订单商品信息表
      OrderGoods orderGoods = new OrderGoods();
      GoodsStokeVO goodsStokeVO = new GoodsStokeVO();
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
      goodsDao.updateAndDeductStoke(goodsStokeVO);
      //删除购物车信息表
      OrdCartGoodsVo ordCartGoodsVo = new OrdCartGoodsVo();
      ordCartGoodsVo.setPropertyId(orderInfoListNow.get(i).getPropertyId());
      ordCartGoodsVo.setMemberId(1L);
      //cartGoodsDao.deleteCartGoods(ordCartGoodsVo);

    }
    orderRe.setShowStatus("已成功生成订单");
    orderRe.setGoodsStatus(1);
    list.add(orderRe);
    return list;
  }

  @Override
  public List<OrderRe> getOrderPage(OrdOrderVo orderVo) {
    List<OrderRe> list;
    list = orderDao.getOrderPage(orderVo);
    if(list.size()<=0){
      return null;
    }
    for(OrderRe orderRe : list){
      OrderEnum orderEnum;
      orderEnum = OrderEnum.getEnumByKey(orderRe.getStatus());
      if (orderEnum != null) {
        orderRe.setShowStatus(orderEnum.getValue());
      }
    }
    return list;
  }

  @Override
  public Integer getOrderTotal(OrdOrderVo orderVo) {
    return orderDao.getOrderTotal( orderVo);
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
  public int logicDelOrder(List<OrderRe> list) {
    return orderDao.logicDelOrder(list);
  }
}

