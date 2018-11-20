package com.uc.training.smadmin.ord.service.impl;


import com.uc.training.common.enums.GoodsStatusEnum;
import com.uc.training.common.enums.OrderEnum;
import com.uc.training.common.enums.UUIDTypeEnum;
import com.uc.training.smadmin.bd.re.AddressRE;
import com.uc.training.smadmin.bd.service.AddressService;
import com.uc.training.smadmin.gds.re.GoodsDetailRE;
import com.uc.training.smadmin.gds.re.GoodsStokeRE;
import com.uc.training.smadmin.gds.service.GoodsService;
import com.uc.training.smadmin.gds.vo.GoodsStokeVO;
import com.uc.training.smadmin.ord.dao.CartGoodsDao;
import com.uc.training.smadmin.ord.dao.OrderDao;
import com.uc.training.smadmin.ord.dao.OrderGoodsDao;
import com.uc.training.smadmin.ord.model.CartGoods;
import com.uc.training.smadmin.ord.model.Order;
import com.uc.training.smadmin.ord.model.OrderGoods;
import com.uc.training.smadmin.ord.re.OrderConfirmRE;
import com.uc.training.smadmin.ord.re.OrderGoodsDetailRE;
import com.uc.training.smadmin.ord.re.OrderInfoRE;
import com.uc.training.smadmin.ord.re.OrderRE;
import com.uc.training.smadmin.ord.re.OrderStatusRE;
import com.uc.training.smadmin.ord.service.OrderService;
import com.uc.training.smadmin.ord.vo.OrdCartGoodsVO;
import com.uc.training.smadmin.ord.vo.OrdGoodsVO;
import com.uc.training.smadmin.ord.vo.OrdMemberVO;
import com.uc.training.smadmin.ord.vo.OrdOrderGoodsVO;
import com.uc.training.smadmin.ord.vo.OrdOrderVO;
import com.uc.training.smadmin.sys.re.OrderSaleRE;
import com.uc.training.smadmin.utils.UUIDUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

    @Autowired
    private AddressService addressService;

    @Override
    public List<CartGoods> getCarGoodsById(Long memberId) {
        return cartGoodsDao.getCartGoodsById(memberId);
    }

    /**
     * 通过会员id来查找
     *
     * @param ordMemberVO （订单表id）
     * @return
     */
    @Override
    public List<Order> getOrderByMemberVO(OrdMemberVO ordMemberVO) {
        return orderDao.getOrderByMemberVO(ordMemberVO);
    }

    @Override
    public List<CartGoods> getCarGoodsByIds(OrdGoodsVO ordGoodsVO) {
        return cartGoodsDao.getCarGoodsByIds(ordGoodsVO);
    }

    /**
     * 获取订单商品列表(提交订单页)
     *
     * @param orderGodsList
     * @return
     */
    @Override
    public List<OrdOrderGoodsVO> getOrderGoodsById(List<OrdOrderGoodsVO> orderGodsList) {
        List<OrdOrderGoodsVO> list = new ArrayList<>();
        OrdGoodsVO ordGoodsVO = new OrdGoodsVO();
        if (!CollectionUtils.isEmpty(orderGodsList)) {
            ordGoodsVO.setMemberId(orderGodsList.get(0).getMemberId());
            List<Long> propertyIds = new ArrayList<>();
            for (int i = 0; i < orderGodsList.size(); i++) {
                propertyIds.add(orderGodsList.get(i).getPropertyId());
            }
            ordGoodsVO.setGoodsPropertyIdList(propertyIds);
        } else {
            return list;
        }
        List<CartGoods> goodsNumList = cartGoodsDao.getCarGoodsByIds(ordGoodsVO);
        OrdOrderGoodsVO ordOrderGoodsVO;
        for (int i = 0; i < orderGodsList.size(); i++) {
            ordOrderGoodsVO = new OrdOrderGoodsVO();
            GoodsDetailRE gdDTO = goodsService.getGoodsDetailByPropertyId(orderGodsList.get(i).getPropertyId());
            if (gdDTO == null) {
                return list;
            }
            ordOrderGoodsVO.setGoodsId(orderGodsList.get(i).getGoodsId());
            ordOrderGoodsVO.setGdsName(gdDTO.getName());
            if (!CollectionUtils.isEmpty(gdDTO.getPicUrl())) {
                ordOrderGoodsVO.setGdsUrl(gdDTO.getPicUrl().get(0).getPicUrl());
            }
            if (!CollectionUtils.isEmpty(goodsNumList)) {
                for (int j = 0; j < goodsNumList.size(); j++) {
                    if (goodsNumList.get(j).getGoodsPropertyId().equals(orderGodsList.get(i).getPropertyId())) {
                        ordOrderGoodsVO.setNum(goodsNumList.get(j).getGoodsNum());
                        break;
                    }
                }
            }
            ordOrderGoodsVO.setPropertyId(orderGodsList.get(i).getPropertyId());
            ordOrderGoodsVO.setProperty(gdDTO.getProperty());
            ordOrderGoodsVO.setSalePrice(gdDTO.getSalePrice());
            ordOrderGoodsVO.setDiscountPrice(gdDTO.getDiscountPrice());
            ordOrderGoodsVO.setStatus(gdDTO.getStatus());
            ordOrderGoodsVO.setIsDiscount(gdDTO.getIsDiscount());
            list.add(ordOrderGoodsVO);
        }
        return list;
    }

    /**
     * 获取订单商品列表(已生成订单)
     *
     * @param orderGodsList
     * @return
     */
    @Override
    public List<OrdOrderGoodsVO> getOrderGoods(List<OrdOrderGoodsVO> orderGodsList, Long orderId) {
        List<OrdOrderGoodsVO> list = new ArrayList<>();
        OrdOrderGoodsVO ordOrderGoodsVO;
        List<OrderGoods> orderGdsList = orderGoodsDao.getOrderGoodsByOrderId(orderId.intValue());
        if (CollectionUtils.isEmpty(orderGdsList)) {
            return null;
        }
        for (int i = 0; i < orderGodsList.size(); i++) {
            ordOrderGoodsVO = new OrdOrderGoodsVO();
            for (int j = 0; j < orderGdsList.size(); j++) {
                if (orderGdsList.get(j).getGoodsPropertyId().equals(orderGodsList.get(i).getPropertyId())) {
                    ordOrderGoodsVO.setSalePrice(orderGdsList.get(j).getSalePrice());
                    ordOrderGoodsVO.setDiscountPrice(orderGdsList.get(j).getDiscountPrice());
                    ordOrderGoodsVO.setPayPrice(orderGdsList.get(j).getPayPrice());
                    ordOrderGoodsVO.setNum(orderGdsList.get(j).getGoodsNum());
                }
            }
            GoodsDetailRE gdDTO = goodsService.getGoodsDetailByPropertyId(orderGodsList.get(i).getPropertyId());
            if (gdDTO == null) {
                return list;
            }
            ordOrderGoodsVO.setGoodsId(orderGodsList.get(i).getGoodsId());
            ordOrderGoodsVO.setGdsName(gdDTO.getName());
            if (!CollectionUtils.isEmpty(gdDTO.getPicUrl())) {
                ordOrderGoodsVO.setGdsUrl(gdDTO.getPicUrl().get(0).getPicUrl());
            }
            ordOrderGoodsVO.setPropertyId(orderGodsList.get(i).getPropertyId());
            ordOrderGoodsVO.setProperty(gdDTO.getProperty());
            ordOrderGoodsVO.setStatus(gdDTO.getStatus());
            ordOrderGoodsVO.setIsDiscount(gdDTO.getIsDiscount());
            list.add(ordOrderGoodsVO);
        }
        return list;
    }

    @Override
    public void addCarGoods(OrdCartGoodsVO ordCartGoodsVO) {
        cartGoodsDao.insertCartGoods(ordCartGoodsVO);
    }

    @Override
    public int deleteCarGoods(OrdCartGoodsVO ordCartGoodsVO) {
        return cartGoodsDao.deleteCartGoods(ordCartGoodsVO);
    }

    @Override
    public int updataCarGoodsNum(OrdCartGoodsVO ordCartGoodsVO) {
        return cartGoodsDao.updataCartGoodsNum(ordCartGoodsVO);
    }

    @Override
    public Integer queryOrderCount(Long memberId) {
        return this.orderDao.queryOrderCount(memberId);
    }

    /**
     * 判断该商品是否下架、删除、无库存
     */
    private List<OrderConfirmRE> validationGoods(List<OrdOrderGoodsVO> orderInfoListNow) {
        List<OrderConfirmRE> list = new ArrayList<>();
        OrderConfirmRE orderConfirmRE = new OrderConfirmRE();
        int a = 2;
        GoodsStokeVO goodsStokeVO;
        if (orderInfoListNow.size() <= a) {
            return list;
        }
        for (int i = 0; i < orderInfoListNow.size() - a; i++) {
            //更新库存表、插入用户订单表和订单商品信息表、删除购物车商品信息,判断商品是否删除或者下架和库存是否足够
            goodsStokeVO = new GoodsStokeVO();
            goodsStokeVO.setPropertyId(orderInfoListNow.get(i).getPropertyId());
            goodsStokeVO.setStoke((long) orderInfoListNow.get(i).getNum());
            GoodsStokeRE goodsStokeRE = goodsService.selectGoodsStatus(goodsStokeVO);
            if (goodsStokeRE.getIsDelete().equals(GoodsStatusEnum.GOODS_DELETE.getType())) {
                StringBuilder temp = new StringBuilder();
                temp.append("您的商品：" + goodsStokeRE.getGoodsName() + "\n" + "规格:");
                temp.append(goodsStokeRE.getGoodsProperty() + "已经被删除了，点击返回购物车，再重新选择");
                orderConfirmRE.setShowStatus(temp.toString());
                orderConfirmRE.setGoodsStatus(OrderEnum.NOORDER.getKey());
                list.add(orderConfirmRE);
                return list;
            }
            if (goodsStokeRE.getStatus().equals(GoodsStatusEnum.GOODS_IS_SHELVES.getType())) {
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
        orderConfirmRE.setGoodsStatus(OrderEnum.WAITPAY.getKey());
        list.add(orderConfirmRE);
        return list;
    }

    /**
     * 提交订单验证信息
     */
    @Override
    public List<OrderConfirmRE> confirmOrderInfo(List<OrdOrderGoodsVO> orderInfoListNow) {
        OrderConfirmRE orderConfirmRE = new OrderConfirmRE();
        List<OrderConfirmRE> list = new ArrayList<>();
        GoodsStokeVO goodsStokeVO;
        // 判断该商品是否下架、删除、无库存
        List<OrderConfirmRE> goodsStatusList = validationGoods(orderInfoListNow);
        if (CollectionUtils.isEmpty(goodsStatusList)) {
            return goodsStatusList;
        } else if (goodsStatusList.get(0).getGoodsStatus() != (int) OrderEnum.WAITPAY.getKey().longValue()) {
            return goodsStatusList;
        }
        // 判断前台传来的价格信息是否与购物车一致
        OrdGoodsVO ordGoodsVO = new OrdGoodsVO();
        ordGoodsVO.setMemberId(orderInfoListNow.get(orderInfoListNow.size() - 2).getMemberId());
        List<Long> propertyIds = new ArrayList<>();
        int a = 2;
        for (int i = 0; i < orderInfoListNow.size() - a; i++) {
            propertyIds.add(orderInfoListNow.get(i).getPropertyId());
        }
        ordGoodsVO.setGoodsPropertyIdList(propertyIds);
        List<CartGoods> goodsNumList = cartGoodsDao.getCarGoodsByIds(ordGoodsVO);
        OrdOrderGoodsVO ordOrderGoodsVO;
        Double memberDiscountPoint = goodsService.getMemberDiscountPoint(orderInfoListNow.get(orderInfoListNow.size() - 2).getMemberId());
        if (memberDiscountPoint == null) {
            return list;
        }
        BigDecimal payPrice = new BigDecimal(0);
        for (int i = 0; i < orderInfoListNow.size() - a; i++) {
            ordOrderGoodsVO = new OrdOrderGoodsVO();
            GoodsDetailRE gdDTO = goodsService.getGoodsDetailByPropertyId(orderInfoListNow.get(i).getPropertyId());
            if (gdDTO == null) {
                return list;
            }
            if (!CollectionUtils.isEmpty(goodsNumList)) {
                for (int j = 0; j < goodsNumList.size(); j++) {
                    if (goodsNumList.get(j).getGoodsPropertyId().equals(orderInfoListNow.get(i).getPropertyId())) {
                        ordOrderGoodsVO.setNum(goodsNumList.get(j).getGoodsNum());
                        if (gdDTO.getIsDiscount() == 1) {
                            payPrice = gdDTO.getDiscountPrice().multiply(BigDecimal.valueOf(goodsNumList.get(j).getGoodsNum() * memberDiscountPoint)).add(payPrice);
                        } else {
                            payPrice = gdDTO.getSalePrice().multiply(BigDecimal.valueOf(goodsNumList.get(j).getGoodsNum() * memberDiscountPoint)).add(payPrice);
                        }
                        break;
                    }
                }
            }
        }
        if (payPrice.compareTo(orderInfoListNow.get(orderInfoListNow.size() - 1).getTotalPrice()) != 0) {
            return list;
        }
        //插入用户订单表
        Order order = new Order();
        order.setMemberId(orderInfoListNow.get(orderInfoListNow.size() - a).getMemberId());
        order.setOrderPrice(orderInfoListNow.get(orderInfoListNow.size() - 1).getOrderPrice());
        order.setPayPrice(orderInfoListNow.get(orderInfoListNow.size() - 1).getTotalPrice());
        //插入地址信息
        AddressRE addressRE = addressService.getAddressById(orderInfoListNow.get(orderInfoListNow.size() - 2).getAddressId());
        if (addressRE != null) {
            order.setReceiptAddress(addressRE.getAddrDetail());
            order.setReceiptName(addressRE.getReceiver());
            order.setReceiptTel(addressRE.getTelephone());
        }
        order.setStatus(OrderEnum.WAITPAY.getKey());
        order.setIsDelete(0);
        //生成订单编号
        order.setOrderNum(UUIDUtil.getUuidByType(UUIDTypeEnum.ORDERID.getType()));
        Long oderId = orderDao.insertOrder(order);
        order.setId(oderId);
        //遍历orderInfoListNow
        OrderGoods orderGoods;
        OrdCartGoodsVO ordCartGoodsVO;
        for (int i = 0; i < orderInfoListNow.size() - a; i++) {
            //插入订单商品信息表
            orderGoods = new OrderGoods();
            goodsStokeVO = new GoodsStokeVO();
            if (oderId != null) {
                orderGoods.setOrderId(oderId);
            }
            orderGoods.setGoodsId(orderInfoListNow.get(i).getGoodsId());
            orderGoods.setPayPrice(orderInfoListNow.get(i).getPayPrice());
            orderGoods.setGoodsPropertyId(orderInfoListNow.get(i).getPropertyId());
            orderGoods.setGoodsNum(orderInfoListNow.get(i).getNum());
            orderGoods.setSalePrice(orderInfoListNow.get(i).getSalePrice());
            orderGoods.setDiscountPrice(orderInfoListNow.get(i).getDiscountPrice());
            orderGoods.setGoodsPropertyId(orderInfoListNow.get(i).getPropertyId());
            orderGoodsDao.insertOrderGoods(orderGoods);
            //更新商品对应的库存
            goodsStokeVO.setStoke(orderGoods.getGoodsNum().longValue());
            goodsStokeVO.setPropertyId(orderGoods.getGoodsPropertyId());
            goodsService.updateAndDeductStoke(goodsStokeVO);
            //删除购物车信息表
            ordCartGoodsVO = new OrdCartGoodsVO();
            ordCartGoodsVO.setPropertyId(orderInfoListNow.get(i).getPropertyId());
            ordCartGoodsVO.setMemberId(orderInfoListNow.get(orderInfoListNow.size() - a).getMemberId());
            cartGoodsDao.deleteCartGoods(ordCartGoodsVO);
        }
        orderConfirmRE.setShowStatus("已成功生成订单");
        orderConfirmRE.setGoodsStatus((int) OrderEnum.WAITPAY.getKey().longValue());
        orderConfirmRE.setOrderId(order.getId());
        list.add(orderConfirmRE);
        return list;
    }

    @Override
    public List<OrderRE> getOrderPage(OrdOrderVO orderVo) {
        return orderDao.getOrderPage(orderVo);
    }

    @Override
    public Integer getOrderTotal(OrdOrderVO orderVo) {
        return orderDao.getOrderTotal(orderVo);
    }

    @Override
    public List<OrderStatusRE> getOrderEnum() {
        List<OrderStatusRE> list = new ArrayList<OrderStatusRE>();
        OrderEnum orderEnum;
        int max = OrderEnum.getMaxKey();
        int i = 0;
        do {
            OrderStatusRE orderStatusRE = new OrderStatusRE();
            orderEnum = OrderEnum.getEnumByKey(i);
            if (orderEnum != null) {
                orderStatusRE.setValue(i);
                orderStatusRE.setLabel(orderEnum.getValue());
                list.add(orderStatusRE);
            }
            i++;
        } while (max >= i);
        OrderStatusRE orderStatusRE = new OrderStatusRE();
        orderStatusRE.setLabel("全部");
        list.add(orderStatusRE);
        return list;
    }

    @Override
    public int logicDelOrder(List<Long> list) {
        return orderDao.logicDelOrder(list);
    }

    @Override
    public int updateOrder(OrdOrderVO ordOrderVO) {
        //如果是取消订单回退库存
        if (ordOrderVO.getStatus().equals(OrderEnum.CANCEL.getKey().longValue())) {
            List<OrderGoodsDetailRE> list = getOrderGdsById(ordOrderVO.getOrderId());
            if (!CollectionUtils.isEmpty(list)) {
                GoodsStokeVO goodsStokeVO = new GoodsStokeVO();
                for (OrderGoodsDetailRE orderGoodsDetailRE : list) {
                    goodsStokeVO.setStoke(-(orderGoodsDetailRE.getGoodsNum().longValue()));
                    goodsStokeVO.setPropertyId(orderGoodsDetailRE.getGoodsPropertyId());
                    goodsService.updateAndDeductStoke(goodsStokeVO);
                }
            } else {
                return 0;
            }
        }
        return orderDao.updateOrder(ordOrderVO);
    }


    @Override
    public List<OrderGoodsDetailRE> getOrderGdsById(Integer id) {
        // 扩展MemberId
        List<OrderGoods> orderGdsList = orderGoodsDao.getOrderGoodsByOrderId(id);
        if (CollectionUtils.isEmpty(orderGdsList)) {
            return null;
        }
        OrderGoodsDetailRE orderGoodsDetailRE;
        GoodsDetailRE gdDTO;
        List<OrderGoodsDetailRE> list = new ArrayList<>();
        for (OrderGoods orderGoods : orderGdsList) {
            orderGoodsDetailRE = new OrderGoodsDetailRE();
            gdDTO = goodsService.getGoodsDetailByPropertyId(orderGoods.getGoodsPropertyId());
            if (gdDTO != null) {
                orderGoodsDetailRE.setGoodsName(gdDTO.getName());
                orderGoodsDetailRE.setGoodsNum(orderGoods.getGoodsNum());
                orderGoodsDetailRE.setGoodsProperty(gdDTO.getProperty());
                orderGoodsDetailRE.setGoodsPrice(gdDTO.getSalePrice());
                orderGoodsDetailRE.setGoodsPrice(gdDTO.getSalePrice());
                orderGoodsDetailRE.setGoodsPropertyId(orderGoods.getGoodsPropertyId());
                if (!CollectionUtils.isEmpty(gdDTO.getPicUrl())) {
                    orderGoodsDetailRE.setGoodsUrl(gdDTO.getPicUrl().get(0).getPicUrl());
                }
                list.add(orderGoodsDetailRE);
            }
        }
        return list;
    }

    /**
     * 根据主键id获取手机号
     *
     * @param id
     * @return
     */
    @Override
    public String getTelephoneById(Long id) {
        return orderDao.getTelephoneById(id);
    }

    /**
     * 根据订单id获取用户订单商品信息
     *
     * @param id
     * @return
     */
    public List<OrderGoodsDetailRE> getOrderGdsByIds(Integer id) {
        List<OrderGoods> orderGdsList = orderGoodsDao.getOrderGoodsByOrderId(id);
        if (CollectionUtils.isEmpty(orderGdsList)) {
            return null;
        }
        GoodsDetailRE gdDTO;
        OrderGoodsDetailRE orderGoodsDetailRE;
        List<OrderGoodsDetailRE> list = new ArrayList<>();
        for (OrderGoods orderGoods : orderGdsList) {
            orderGoodsDetailRE = new OrderGoodsDetailRE();
            gdDTO = goodsService.getGoodsDetailByPropertyId(orderGoods.getGoodsPropertyId());
            if (gdDTO != null) {
                orderGoodsDetailRE.setGoodsPrice(orderGoods.getSalePrice());
                orderGoodsDetailRE.setGoodsName(gdDTO.getName());
                orderGoodsDetailRE.setDiscountPrice(orderGoods.getDiscountPrice());
                orderGoodsDetailRE.setIsDiscount(gdDTO.getIsDiscount());
                orderGoodsDetailRE.setGoodsNum(orderGoods.getGoodsNum());
                orderGoodsDetailRE.setGoodsProperty(gdDTO.getProperty());
                orderGoodsDetailRE.setPayPrice(orderGoods.getPayPrice());
                orderGoodsDetailRE.setGoodsPropertyId(orderGoods.getGoodsPropertyId());
                orderGoodsDetailRE.setGoodsId(gdDTO.getGoodsId());
                if (!CollectionUtils.isEmpty(gdDTO.getPicUrl())) {
                    orderGoodsDetailRE.setGoodsUrl(gdDTO.getPicUrl().get(0).getPicUrl());
                }
                list.add(orderGoodsDetailRE);
            }
        }
        return list;
    }

    /**
     * 根据会员id获取订单信息
     *
     * @param ordMemberVO
     * @return
     */
    @Override
    public List<OrderInfoRE> getOrderInfoListByMemberId(OrdMemberVO ordMemberVO) {
        //先获该用户的取订单id，然后查询每条订单的状态，订单的金额 以及获取订单的商品信息
        List<OrderInfoRE> orderInfoREList = new ArrayList<>();
        List<Order> orderList = orderDao.getOrderByMemberVO(ordMemberVO);
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
            orderInfoRE.setOrderId(order.getId().intValue());
            orderInfoRE.setReceiptName(order.getReceiptName());
            orderInfoRE.setReceiptTel(order.getReceiptTel());
            orderInfoRE.setReceiptAddress(order.getReceiptAddress());
            List<OrderGoodsDetailRE> orderGodsList = getOrderGdsByIds(order.getId().intValue());
            orderInfoRE.setOrderGoodsDetailRE(orderGodsList);
            orderInfoREList.add(orderInfoRE);
        }
        OrdOrderVO ordOrderVO = new OrdOrderVO();
        ordOrderVO.setMemberId(ordMemberVO.getMemberId());
        ordOrderVO.setStatus(ordMemberVO.getStatus());
        orderInfoRE = new OrderInfoRE();
        orderInfoRE.setTotalOrderNum(orderDao.getOrderTotal(ordOrderVO).longValue());
        orderInfoREList.add(orderInfoRE);
        return orderInfoREList;
    }


    @Override
    public OrderSaleRE getOrderSaleData() {
        return orderDao.getOrderSaleData();
    }

    @Override
    public Integer queryCartGoodsCount(Long memberId) {
        return cartGoodsDao.queryCartGoodsCount(memberId);
    }
}

