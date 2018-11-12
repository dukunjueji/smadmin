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
import com.uc.training.smadmin.ord.re.OrderGoodsDetailRe;
import com.uc.training.smadmin.ord.re.OrderInfoRE;
import com.uc.training.smadmin.ord.re.OrderRe;
import com.uc.training.smadmin.ord.re.OrderStatusRe;
import com.uc.training.smadmin.ord.service.OrderService;
import com.uc.training.smadmin.ord.vo.OrdCartGoodsVo;
import com.uc.training.smadmin.ord.vo.OrdGoodsVO;
import com.uc.training.smadmin.ord.vo.OrdMemberVO;
import com.uc.training.smadmin.ord.vo.OrdOrderGoodsVo;
import com.uc.training.smadmin.ord.vo.OrdOrderVo;
import com.uc.training.smadmin.sys.re.OrderSaleRE;
import com.uc.training.smadmin.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
    public List<Order> getOrderById(OrdMemberVO ordMemberVO) {
        return orderDao.getOrderById(ordMemberVO);
    }

    @Override
    public List<CartGoods> getCarGoodsByIds(OrdGoodsVO ordGoodsVO) {
        return cartGoodsDao.getCarGoodsByIds(ordGoodsVO);
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
            if (!CollectionUtils.isEmpty(gdDTO.getPicUrl())) {
                ordOrderGoodsVo.setGdsUrl(gdDTO.getPicUrl().get(0).getPicUrl());
            }
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

    /**
     * 获取订单商品列表(已生成订单)
     *
     * @param orderGodsList
     * @return
     */
    @Override
    public List<OrdOrderGoodsVo> getOrderGoods(List<OrdOrderGoodsVo> orderGodsList, Long orderId) {
        List<OrdOrderGoodsVo> list = new ArrayList<>();
        OrdOrderGoodsVo ordOrderGoodsVo;
        List<OrderGoods> orderGdsList = orderGoodsDao.getOrderGoodsByOrderId(orderId.intValue());
        if (CollectionUtils.isEmpty(orderGdsList)) {
            return null;
        }
        for (int i = 0; i < orderGodsList.size(); i++) {
            ordOrderGoodsVo = new OrdOrderGoodsVo();
            for (int j = 0; j < orderGdsList.size(); j++) {
                if (orderGdsList.get(j).getGoodsPropertyId().equals(orderGodsList.get(i).getPropertyId())) {
                    ordOrderGoodsVo.setSalePrice(orderGdsList.get(j).getSalePrice());
                    ordOrderGoodsVo.setDiscountPrice(orderGdsList.get(j).getDiscountPrice());
                    ordOrderGoodsVo.setPayPrice(orderGdsList.get(j).getPayPrice());
                }
            }
            GoodsDetailRE gdDTO = goodsService.getGoodsDetailByPropertyId(orderGodsList.get(i).getPropertyId());
            if (gdDTO == null) {
                return list;
            }
            ordOrderGoodsVo.setGoodsId(orderGodsList.get(i).getGoodsId());
            ordOrderGoodsVo.setGdsName(gdDTO.getName());
            if (!CollectionUtils.isEmpty(gdDTO.getPicUrl())) {
                ordOrderGoodsVo.setGdsUrl(gdDTO.getPicUrl().get(0).getPicUrl());
            }
            ordOrderGoodsVo.setPropertyId(orderGodsList.get(i).getPropertyId());
            ordOrderGoodsVo.setProperty(gdDTO.getProperty());
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
     * 判断该商品是否下架、删除、无库存
     */
    private List<OrderConfirmRE> validationGoods(List<OrdOrderGoodsVo> orderInfoListNow) {
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
    public List<OrderConfirmRE> confirmOrderInfo(List<OrdOrderGoodsVo> orderInfoListNow) {
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
        //插入用户订单表
        Order order = new Order();
        order.setMemberId(orderInfoListNow.get(orderInfoListNow.size() - 2).getMemberId());
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
        OrdCartGoodsVo ordCartGoodsVo;
        int a = 2;
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
            ordCartGoodsVo = new OrdCartGoodsVo();
            ordCartGoodsVo.setPropertyId(orderInfoListNow.get(i).getPropertyId());
            ordCartGoodsVo.setMemberId(orderInfoListNow.get(orderInfoListNow.size() - 2).getMemberId());
            cartGoodsDao.deleteCartGoods(ordCartGoodsVo);
        }
        orderConfirmRE.setShowStatus("已成功生成订单");
        orderConfirmRE.setGoodsStatus((int) OrderEnum.WAITPAY.getKey().longValue());
        orderConfirmRE.setOrderId(order.getId());
        list.add(orderConfirmRE);
        return list;
    }

    @Override
    public List<OrderRe> getOrderPage(OrdOrderVo orderVo) {
        return orderDao.getOrderPage(orderVo);
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
        //如果是取消订单回退库存
        if (ordOrderVo.getStatus().equals(OrderEnum.CANCEL.getKey().longValue())) {
            List<OrderGoodsDetailRe> list = getOrderGdsById(ordOrderVo.getOrderId());
            if (!CollectionUtils.isEmpty(list)) {
                GoodsStokeVO goodsStokeVO = new GoodsStokeVO();
                for (OrderGoodsDetailRe orderGoodsDetailRe : list) {
                    goodsStokeVO.setStoke(-(orderGoodsDetailRe.getGoodsNum().longValue()));
                    goodsStokeVO.setPropertyId(orderGoodsDetailRe.getGoodsPropertyId());
                    goodsService.updateAndDeductStoke(goodsStokeVO);
                }
            } else {
                return 0;
            }
        }
        return orderDao.updateOrder(ordOrderVo);
    }


    @Override
    public List<OrderGoodsDetailRe> getOrderGdsById(Integer id) {
        // 扩展MemberId
        List<OrderGoods> orderGdsList = orderGoodsDao.getOrderGoodsByOrderId(id);
        if (CollectionUtils.isEmpty(orderGdsList)) {
            return null;
        }
        OrderGoodsDetailRe orderGoodsDetailRe;
        GoodsDetailRE gdDTO;
        List<OrderGoodsDetailRe> list = new ArrayList<>();
        for (OrderGoods orderGoods : orderGdsList) {
            orderGoodsDetailRe = new OrderGoodsDetailRe();
            gdDTO = goodsService.getGoodsDetailByPropertyId(orderGoods.getGoodsPropertyId());
            if (gdDTO != null) {
                orderGoodsDetailRe.setGoodsName(gdDTO.getName());
                orderGoodsDetailRe.setGoodsNum(orderGoods.getGoodsNum());
                orderGoodsDetailRe.setGoodsProperty(gdDTO.getProperty());
                orderGoodsDetailRe.setGoodsPrice(gdDTO.getSalePrice());
                orderGoodsDetailRe.setGoodsPrice(gdDTO.getSalePrice());
                orderGoodsDetailRe.setGoodsPropertyId(orderGoods.getGoodsPropertyId());
                if (!CollectionUtils.isEmpty(gdDTO.getPicUrl())) {
                    orderGoodsDetailRe.setGoodsUrl(gdDTO.getPicUrl().get(0).getPicUrl());
                }
                list.add(orderGoodsDetailRe);
            }
        }
        return list;
    }

    /**
     * 根据订单号获取手机号
     *
     * @param orderNum
     * @return
     */
    @Override
    public String getTelephoneByOrderNum(String orderNum) {
        return orderDao.getTelephoneByOrderNum(orderNum);
    }

    /**
     * 根据订单id获取用户订单商品信息
     *
     * @param id
     * @return
     */
    public List<OrderGoodsDetailRe> getOrderGdsByIds(Integer id) {
        List<OrderGoods> orderGdsList = orderGoodsDao.getOrderGoodsByOrderId(id);
        if (CollectionUtils.isEmpty(orderGdsList)) {
            return null;
        }
        GoodsDetailRE gdDTO;
        OrderGoodsDetailRe orderGoodsDetailRe;
        List<OrderGoodsDetailRe> list = new ArrayList<>();
        for (OrderGoods orderGoods : orderGdsList) {
            orderGoodsDetailRe = new OrderGoodsDetailRe();
            gdDTO = goodsService.getGoodsDetailByPropertyId(orderGoods.getGoodsPropertyId());
            if (gdDTO != null) {
                orderGoodsDetailRe.setGoodsPrice(orderGoods.getSalePrice());
                orderGoodsDetailRe.setGoodsName(gdDTO.getName());
                orderGoodsDetailRe.setDiscountPrice(orderGoods.getDiscountPrice());
                orderGoodsDetailRe.setIsDiscount(gdDTO.getIsDiscount());
                orderGoodsDetailRe.setGoodsNum(orderGoods.getGoodsNum());
                orderGoodsDetailRe.setGoodsProperty(gdDTO.getProperty());
                orderGoodsDetailRe.setPayPrice(orderGoods.getPayPrice());
                orderGoodsDetailRe.setGoodsPropertyId(orderGoods.getGoodsPropertyId());
                orderGoodsDetailRe.setGoodsId(gdDTO.getGoodsId());
                if (!CollectionUtils.isEmpty(gdDTO.getPicUrl())) {
                    orderGoodsDetailRe.setGoodsUrl(gdDTO.getPicUrl().get(0).getPicUrl());
                }
                list.add(orderGoodsDetailRe);
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
            orderInfoRE.setOrderId(order.getId().intValue());
            orderInfoRE.setReceiptName(order.getReceiptName());
            orderInfoRE.setReceiptTel(order.getReceiptTel());
            orderInfoRE.setReceiptAddress(order.getReceiptAddress());
            List<OrderGoodsDetailRe> orderGodsList = getOrderGdsByIds(order.getId().intValue());
            orderInfoRE.setOrderGoodsDetailRe(orderGodsList);
            orderInfoREList.add(orderInfoRE);
        }
        OrdOrderVo ordOrderVo = new OrdOrderVo();
        ordOrderVo.setMemberId(ordMemberVO.getMemberId());
        ordOrderVo.setStatus(ordMemberVO.getStatus());
        orderInfoRE = new OrderInfoRE();
        orderInfoRE.setTotalOrderNum(orderDao.getOrderTotal(ordOrderVo).longValue());
        orderInfoREList.add(orderInfoRE);
        return orderInfoREList;
    }


    @Override
    public OrderSaleRE getOrderSaleData() {
        return orderDao.getOrderSaleData();
    }
}

