package com.uc.training.smadmin.ord.service.impl;

import com.uc.training.smadmin.gds.dao.GoodsDao;
import com.uc.training.smadmin.gds.re.GoodsDetailRE;
import com.uc.training.smadmin.ord.dao.CartGoodsDao;
import com.uc.training.smadmin.ord.dao.OrderDao;
import com.uc.training.smadmin.ord.dao.OrderGoodsDao;
import com.uc.training.smadmin.ord.model.CartGoods;
import com.uc.training.smadmin.ord.model.Order;
import com.uc.training.smadmin.ord.model.OrderGoods;
import com.uc.training.smadmin.ord.service.OrderService;
import com.uc.training.smadmin.ord.vo.OrdCartGoodsVo;
import com.uc.training.smadmin.ord.vo.OrdOrderGoodsVo;
import com.uc.training.smadmin.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
   * 更新订单信息
   */
  @Override
  public void updateOrderInfo(List<OrdOrderGoodsVo> orderInfoListNow) {
      //orderInfoListNow里最后面的两个对象，分别存放提交订单页面的收货人信息、商品总额
    Order order = new Order();
    order.setMemberId(1L);
    order.setOrderPrice(orderInfoListNow.get(orderInfoListNow.size()-1).getTotalPrice());
    order.setPayPrice(orderInfoListNow.get(orderInfoListNow.size()-1).getTotalPrice());
    order.setReceiptAddress(orderInfoListNow.get(orderInfoListNow.size()-2).getReceiptAddress());
    order.setReceiptName(orderInfoListNow.get(orderInfoListNow.size()-2).getReceiptName());
    order.setReceiptTel(orderInfoListNow.get(orderInfoListNow.size()-2).getReceiptTel());
    order.setStatus(1);
    order.setIsDelete(0);
    //order.setOrderNum(UUIDUtil.getUuidByType(UUIDTypeEnum.ORDERID.getType()));
    Long oderId = orderDao.insertOrder(order);
    for (int i = 0; i < orderInfoListNow.size()-2; i++){
        OrderGoods orderGoods = new OrderGoods();
        orderGoods.setOrderId(oderId);
        orderGoods.setGoodsId(orderInfoListNow.get(i).getGoodsId());
        orderGoods.setPayPrice(orderInfoListNow.get(i).getSalePrice());
        orderGoods.setGoodsPropertyId(orderInfoListNow.get(i).getPropertyId());
        orderGoods.setGoodsNum(orderInfoListNow.get(i).getNum());
        orderGoods.setSalePrice(orderInfoListNow.get(i).getSalePrice());
        orderGoods.setDiscountPrice(0);
        orderGoodsDao.insertOrderGoods(orderGoods);
    }


  }
}
