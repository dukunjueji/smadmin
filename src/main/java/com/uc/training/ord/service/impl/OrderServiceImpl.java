package com.uc.training.ord.service.impl;

import com.uc.training.common.enums.GoodsStatusEnum;
import com.uc.training.common.enums.OrderEnum;
import com.uc.training.common.enums.UUIDTypeEnum;
import com.uc.training.common.utils.UUIDUtil;
import com.uc.training.common.vo.RemoteResult;
import com.uc.training.ord.re.CartGoodsRE;
import com.uc.training.ord.re.OrderConfirmRE;
import com.uc.training.ord.re.OrderGoodsDetailRE;
import com.uc.training.ord.re.OrderGoodsRE;
import com.uc.training.ord.re.OrderInfoRE;
import com.uc.training.ord.re.OrderRE;
import com.uc.training.ord.service.OrderService;
import com.uc.training.ord.vo.OrdCartGoodsVO;
import com.uc.training.ord.vo.OrdGoodsVO;
import com.uc.training.ord.vo.OrdMemberVO;
import com.uc.training.ord.vo.OrdOrderGoodsVO;
import com.uc.training.ord.vo.OrdOrderVO;
import com.uc.training.ord.vo.OrderGoodsVO;
import com.uc.training.ord.vo.OrderVO;
import com.uc.training.remoteClient.OrderClient;
import com.uc.training.smadmin.bd.re.AddressRE;
import com.uc.training.smadmin.gds.re.GoodsDetailRE;
import com.uc.training.smadmin.gds.re.GoodsStokeRE;
import com.uc.training.smadmin.gds.vo.GoodsStokeVO;
import com.uc.training.smadmin.sys.re.OrderSaleRE;
import com.zuche.framework.remote.RemoteClientFactory;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    /** 根据用户id查询购物车信息表*/
    private static final String GET_CAR_GOODS_BY_ID = "smorder.api.getCarGoodsById";
    /** 更新购物车商品数量*/
    private static final String UPDATE_CAR_GOODS_NUM = "smorder.api.updateCarGoodsNum";
    /** 根据用户ID和购物车商品表ID获取购物车商品信息*/
    private static final String GET_CAR_GOODS_BY_IDS = "smorder.api.getCarGoodsByIds";
    /** 根据用户id 获取购物车商品表数据数量*/
    private static final String GET_ORDER_GOODS_BY_ORDER_ID = "smorder.api.getOrderGoodsByOrderId";
    /** 加入购物车*/
    private static final String ADD_CAR_GOODS = "smorder.api.addCarGoods";
    /** 删除购物车*/
    private static final String DELETE_CAR_GOODS = "smorder.api.deleteCarGoods";
    /** 插入订单信息并返回订单id*/
    private static final String INSERT_ORDER = "smorder.api.insertOrder";
    /** 插入订单商品信息*/
    private static final String INSERT_ORDER_GOODS = "smorder.api.insertOrderGoods";



    /**根据用户id查询购物车信息表
     *
     * @param memberId
     * @return
     */
    @Autowired
    OrderClient orderClient;

    @Override
    public List<CartGoodsRE> getCarGoodsById(Long memberId) {
        return orderClient.getCarGoodsById(memberId);
    }

    /**
     * 更新购物车商品数量
     *
     * @param ordCartGoodsVO
     * @return 返回影响条数
     */
    @Override
    public int updateCarGoodsNum(OrdCartGoodsVO ordCartGoodsVO) {
        RemoteResult<Integer> re = (RemoteResult<Integer>) RemoteClientFactory.getInstance().executeToObject(UPDATE_CAR_GOODS_NUM, ordCartGoodsVO);
        return re.getRe();
    }

    /**
     * 通过会员id来查找
     *
     * @param ordMemberVO （订单表id）
     * @return
     */
    @Override
    public List<OrderRE> getOrderByMemberVO(OrdMemberVO ordMemberVO) {
        RemoteResult<List<OrderRE>> re = (RemoteResult<List<OrderRE>>) RemoteClientFactory.getInstance().executeToObject(UPDATE_CAR_GOODS_NUM, ordCartGoodsVO);
        return orderDao.getOrderByMemberVO(ordMemberVO);
    }

    @Override
    public List<CartGoodsRE> getCarGoodsByIds(OrdGoodsVO ordGoodsVO) {
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
        RemoteResult<List<CartGoodsRE>> re = (RemoteResult<List<CartGoodsRE>>) RemoteClientFactory.getInstance().executeToObject(GET_CAR_GOODS_BY_IDS, ordGoodsVO);
        List<CartGoodsRE> goodsNumList = re.getRe();
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
        RemoteResult<List<OrderGoodsRE>> re = (RemoteResult<List<OrderGoodsRE>>) RemoteClientFactory.getInstance().executeToObject(GET_ORDER_GOODS_BY_ORDER_ID, orderId.intValue());
        List<OrderGoodsRE> orderGdsList = re.getRe();
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

    /**
     * 加入购物车
     * @param ordCartGoodsVO
     * @return
     */
    @Override
    public Integer addCarGoods(OrdCartGoodsVO ordCartGoodsVO) {
        RemoteResult<Integer> re = (RemoteResult<Integer>)RemoteClientFactory.getInstance().executeToObject(ADD_CAR_GOODS, ordCartGoodsVO);
        return re.getRe();
    }

    /**
     * 删除购物车商品
     * @param ordCartGoodsVO
     * @return
     */
    @Override
    public Integer deleteCarGoods(OrdCartGoodsVO ordCartGoodsVO) {
        RemoteResult<Integer> re = (RemoteResult<Integer>)RemoteClientFactory.getInstance().executeToObject(DELETE_CAR_GOODS, ordCartGoodsVO);
        return re.getRe();
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
        RemoteResult<List<CartGoodsRE>> re = (RemoteResult<List<CartGoodsRE>>) RemoteClientFactory.getInstance().executeToObject(GET_CAR_GOODS_BY_IDS, ordGoodsVO);
        List<CartGoodsRE> goodsNumList = re.getRe();
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
                        payPrice = payPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
                        break;
                    }
                }
            }
        }
        if (payPrice.compareTo(orderInfoListNow.get(orderInfoListNow.size() - 1).getTotalPrice()) != 0) {
            return list;
        }
        //插入用户订单表
        OrderVO order = new OrderVO();
        order.setMemberId(orderInfoListNow.get(orderInfoListNow.size() - a).getMemberId());
        order.setOrderPrice(orderInfoListNow.get(orderInfoListNow.size() - 1).getOrderPrice());
        order.setPayPrice(orderInfoListNow.get(orderInfoListNow.size() - 1).getTotalPrice());
        //插入地址信息
        AddressRE addressRE = addressService.getAddressById(orderInfoListNow.get(orderInfoListNow.size() - 2).getAddressId());
        if (addressRE != null) {
            order.setReceiptAddress(addressRE.getProvince() + addressRE.getCity() + addressRE.getDistrict() + addressRE.getAddrDetail());
            order.setReceiptName(addressRE.getReceiver());
            order.setReceiptTel(addressRE.getTelephone());
        }
        order.setStatus(OrderEnum.WAITPAY.getKey());
        order.setIsDelete(0);
        //生成订单编号
        order.setOrderNum(UUIDUtil.getUuidByType(UUIDTypeEnum.ORDERID.getType()));
        RemoteResult<Long> orderId = (RemoteResult<Long>) RemoteClientFactory.getInstance().executeToObject(INSERT_ORDER, ordGoodsVO);
        Long oderId = orderId.getRe();
        order.setId(oderId);
        //遍历orderInfoListNow
        OrderGoodsVO orderGoods;
        OrdCartGoodsVO ordCartGoodsVO;
        for (int i = 0; i < orderInfoListNow.size() - a; i++) {
            //插入订单商品信息表
            orderGoods = new OrderGoodsVO();
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
            RemoteClientFactory.getInstance().executeToObject(INSERT_ORDER_GOODS, ordGoodsVO);
            //更新商品对应的库存
            goodsStokeVO.setStoke(orderGoods.getGoodsNum().longValue());
            goodsStokeVO.setPropertyId(orderGoods.getGoodsPropertyId());
            goodsService.updateAndDeductStoke(goodsStokeVO);
            //删除购物车信息表
            ordCartGoodsVO = new OrdCartGoodsVO();
            ordCartGoodsVO.setPropertyId(orderInfoListNow.get(i).getPropertyId());
            ordCartGoodsVO.setMemberId(orderInfoListNow.get(orderInfoListNow.size() - a).getMemberId());
            deleteCarGoods(ordCartGoodsVO);
        }
        orderConfirmRE.setShowStatus("已成功生成订单");
        orderConfirmRE.setGoodsStatus((int) OrderEnum.WAITPAY.getKey().longValue());
        orderConfirmRE.setOrderId(order.getId());
        list.add(orderConfirmRE);
        return list;
    }

    @Override
    public List<OrderRe> getOrderPage(OrdOrderVO orderVO) {
        return orderDao.getOrderPage(orderVO);
    }

    @Override
    public Integer getOrderTotal(OrdOrderVO orderVO) {
        return orderDao.getOrderTotal(orderVO);
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

    /**
     * 更新订单状态
     *
     * @param ordOrderVO
     * @return int
     */
    @Override
    public int updateOrder(OrdOrderVO ordOrderVO) {
        return 0;
    }

    @Override
    public int updateOrder(OrdOrderVO ordOrderVO) {
        OrdOrderVO getOrdOrderVO= orderDao.getOrderByOrcerNum(ordOrderVO.getOrderNum());
        if (getOrdOrderVO == null){
            return 0;
        }
        //如果是取消订单回退库存
        if (ordOrderVO.getStatus().equals(OrderEnum.CANCEL.getKey().longValue())) {
            //需要取消订单的状态为待付款状态
            if (!getOrdOrderVO.getStatus().equals(OrderEnum.WAITPAY.getKey().longValue())){
                return 0;
            }
            List<OrderGoodsDetailRe> list = getOrderGdsById(ordOrderVO.getOrderId());
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
        return orderDao.updateOrder(ordOrderVO);
    }


    @Override
    public List<OrderGoodsDetailRE> getOrderGdsById(Integer id) {
        RemoteResult<List<OrderGoodsRE>> re = (RemoteResult<List<OrderGoodsRE>>) RemoteClientFactory.getInstance().executeToObject(GET_ORDER_GOODS_BY_ORDER_ID, id);
        List<OrderGoodsRE> orderGdsList = re.getRe();
        if (CollectionUtils.isEmpty(orderGdsList)) {
            return null;
        }
        OrderGoodsDetailRE orderGoodsDetailRe;
        GoodsDetailRE gdDTO;
        List<OrderGoodsDetailRE> list = new ArrayList<>();
        for (OrderGoodsRE orderGoods : orderGdsList) {
            orderGoodsDetailRe = new OrderGoodsDetailRE();
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
    private List<OrderGoodsDetailRe> getOrderGdsByIds(Integer id) {
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
                orderGoodsDetailRe.setStock(gdDTO.getStock().intValue());
                orderGoodsDetailRe.setIsDelete(gdDTO.getIsDelete().intValue());
                orderGoodsDetailRe.setStatus(gdDTO.getStatus().intValue());
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
            List<OrderGoodsDetailRe> orderGodsList = getOrderGdsByIds(order.getId().intValue());
            orderInfoRE.setOrderGoodsDetailRe(orderGodsList);
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

    /**
     * 获取订单商品列表(已生成订单)
     *
     * @param orderGodsList
     * @param orderId
     * @return
     */
    @Override
    public List<OrdOrderGoodsVO> getOrderGoods(List<OrdOrderGoodsVO> orderGodsList, Long orderId) {
        return null;
    }


    @Override
    public OrderSaleRE getOrderSaleData() {
        return orderDao.getOrderSaleData();
    }

    @Override
    public Integer queryCartGoodsCount(Long memberId) {
        return cartGoodsDao.queryCartGoodsCount(memberId);
    }

    @Override
    public List<CommentVO> getCommentOrderGoodsDetail(OrdGoodsVO ordGoodsVO) {
        List<CommentVO> list = orderDao.getPropertyIdListByUid(ordGoodsVO);
        GoodsDetailRE gdDTO = new GoodsDetailRE();
        int conut = 0;
        // 判空
        if(CollectionUtils.isEmpty(list)){
            return null;
        }
        // 记录上一个商品属性Id 如果相同不需要再次查库
        Long recomentId = list.get(0).getGoodsPropertyId();
        // 获取未评价商品详情
        for (CommentVO commentVO:list) {
            // 第一次进入
            if(conut == 0){
                gdDTO = goodsService.getGoodsDetailByPropertyId(commentVO.getGoodsPropertyId());
                commentVO.setGoodsId(gdDTO.getGoodsId());
                commentVO.setGoodsProperty(gdDTO.getProperty());
                commentVO.setStatus(gdDTO.getStatus());
                commentVO.setGoodsName(gdDTO.getName());
                if (!CollectionUtils.isEmpty(gdDTO.getPicUrl())) {
                    commentVO.setImgUrl(gdDTO.getPicUrl().get(0).getPicUrl());
                }
                conut ++;
                recomentId = commentVO.getGoodsPropertyId();
                continue;
            }
            // 查找与上一次属性id相同时
            if (recomentId.equals(commentVO.getGoodsPropertyId())) {
                commentVO.setGoodsId(gdDTO.getGoodsId());
                commentVO.setStatus(gdDTO.getStatus());
                commentVO.setGoodsName(gdDTO.getName());
                commentVO.setGoodsProperty(gdDTO.getProperty());
                if (CollectionUtils.isEmpty(gdDTO.getPicUrl())) {
                    commentVO.setImgUrl(gdDTO.getPicUrl().get(0).getPicUrl());
                }
                continue;
            }
            gdDTO = goodsService.getGoodsDetailByPropertyId(commentVO.getGoodsPropertyId());
            commentVO.setGoodsId(gdDTO.getGoodsId());
            commentVO.setGoodsProperty(gdDTO.getProperty());
            commentVO.setGoodsName(gdDTO.getName());
            commentVO.setStatus(gdDTO.getStatus());
            if (CollectionUtils.isEmpty(gdDTO.getPicUrl())) {
                commentVO.setImgUrl(gdDTO.getPicUrl().get(0).getPicUrl());
            }
            recomentId = commentVO.getGoodsPropertyId();
        }
        return list;
    }
}

