package com.uc.training.smadmin.ord.controller;

import com.uc.training.common.annotation.AccessLogin;
import com.uc.training.common.base.controller.BaseController;
import com.uc.training.common.enums.GoodsStatusEnum;
import com.uc.training.common.enums.OrderEnum;
import com.uc.training.common.enums.SmsTypeEnum;
import com.uc.training.smadmin.bd.service.MemberService;
import com.uc.training.smadmin.bd.vo.MemberInfoVO;
import com.uc.training.smadmin.gds.re.GoodsDetailRE;
import com.uc.training.smadmin.gds.service.GoodsService;
import com.uc.training.smadmin.mq.vo.MqVO;
import com.uc.training.smadmin.ord.dao.OrderDao;
import com.uc.training.smadmin.ord.model.CartGoods;
import com.uc.training.smadmin.ord.model.Order;
import com.uc.training.smadmin.ord.re.OrderConfirmRE;
import com.uc.training.smadmin.ord.re.OrderGoodsDetailRe;
import com.uc.training.smadmin.ord.re.OrderInfoRE;
import com.uc.training.smadmin.ord.service.OrderService;
import com.uc.training.smadmin.ord.vo.OrdCartGoodsVo;
import com.uc.training.smadmin.ord.vo.OrdGoodsVO;
import com.uc.training.smadmin.ord.vo.OrdMemberVO;
import com.uc.training.smadmin.ord.vo.OrdOrderGoodsVo;
import com.uc.training.smadmin.ord.vo.OrdOrderVo;
import com.uc.training.smadmin.sms.vo.GenerateSmsVO;
import com.ycc.base.common.Result;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单控制类
 *
 * @author DK
 * @date 20181021
 */
@Controller
@RequestMapping(value = "/api/order/")
public class OrderController extends BaseController {
    @Autowired
    OrderService orderService;

    @Autowired
    GoodsService goodsService;

    @Autowired
    MemberService memberService;

    @Autowired
    OrderDao orderDao;

    /**
     * 获取购物车用户商品列表
     *
     * @return
     * @author hhj
     */
    @ResponseBody
    @RequestMapping(value = "getCartList.do_", method = RequestMethod.GET)
    public Result getCartgds() {
        List<OrdCartGoodsVo> list = new ArrayList<>();
        List<CartGoods> cartList = orderService.getCarGoodsById(getUid());
        //判空
        if (CollectionUtils.isEmpty(cartList)) {
            return Result.getSuccessResult(null);
        }
        OrdCartGoodsVo ordCartgoodsVo;
        GoodsDetailRE gdDTO;
        for (CartGoods cartGoods : cartList) {
            ordCartgoodsVo = new OrdCartGoodsVo();
            gdDTO = goodsService.getGoodsDetailByPropertyId(cartGoods.getGoodsPropertyId());
            if (gdDTO != null) {
                ordCartgoodsVo.setCartId(cartGoods.getId());
                ordCartgoodsVo.setGoodsId(gdDTO.getGoodsId());
                ordCartgoodsVo.setGdsName(gdDTO.getName());
                if (!CollectionUtils.isEmpty(gdDTO.getPicUrl())) {
                    ordCartgoodsVo.setGdsUrl(gdDTO.getPicUrl().get(0).getPicUrl());
                }
                ordCartgoodsVo.setPropertyId(cartGoods.getGoodsPropertyId());
                ordCartgoodsVo.setProperty(gdDTO.getProperty());
                ordCartgoodsVo.setSalePrice(gdDTO.getSalePrice());
                ordCartgoodsVo.setDiscountPrice(gdDTO.getDiscountPrice());
                ordCartgoodsVo.setStatus(gdDTO.getStatus());
                ordCartgoodsVo.setIsDiscount(gdDTO.getIsDiscount());
                ordCartgoodsVo.setNum(cartGoods.getGoodsNum().longValue());
                ordCartgoodsVo.setStock(gdDTO.getStock());
                list.add(ordCartgoodsVo);
            } else {
                return Result.getBusinessException("获取异常", null);
            }
        }
        return Result.getSuccessResult(list);
    }

    /**
     * 更改购物车信息（更改商品数量）
     *
     * @param request
     * @param ordCartGoodsVo
     * @return
     * @author hhj
     */
    @ResponseBody
    @AccessLogin
    @RequestMapping(value = "updataCartgoods.do_", method = RequestMethod.POST)
    public Result updataCartgds(HttpServletRequest request, OrdCartGoodsVo ordCartGoodsVo) {
        ordCartGoodsVo.setMemberId(getUid());
        GoodsDetailRE gdDTO = goodsService.getGoodsDetailByPropertyId(ordCartGoodsVo.getPropertyId());
        //判空
        if (gdDTO == null) {
            return Result.getBusinessException("获取异常", null);
        }
        // 更改数据判断库存如果库存不足更新为当前库存量并返回当前库存数据
        if (gdDTO.getStock() <= ordCartGoodsVo.getNum()) {
            ordCartGoodsVo.setNum(gdDTO.getStock());
            orderService.updataCarGoodsNum(ordCartGoodsVo);
            return Result.getSuccessResult(gdDTO.getStock());
        }
        orderService.updataCarGoodsNum(ordCartGoodsVo);
        return Result.getSuccessResult(gdDTO.getStock());
    }

    /**
     * 获取订单列表(从购物车接口进入)
     *
     * @param goodsList
     * @return
     * @author DK
     */
    @ResponseBody
    @RequestMapping(value = "getOrderList.do_", method = RequestMethod.GET)
    public Result<List<OrdOrderGoodsVo>> getOrderGds(String goodsList) {
        List<OrdOrderGoodsVo> orderGodsList = (List<OrdOrderGoodsVo>) JSONArray.toList(JSONArray.fromObject(goodsList), new OrdOrderGoodsVo(), new JsonConfig());
        if (CollectionUtils.isEmpty(orderGodsList)) {
            return Result.getSuccessResult(null);
        }
        orderGodsList.get(0).setMemberId(getUid());
        List<OrdOrderGoodsVo> orderList = orderService.getOrderGoodsById(orderGodsList);
        if (CollectionUtils.isEmpty(orderList)) {
            return Result.getBusinessException("获取订单列表失败", "");
        }
        return Result.getSuccessResult(orderList);
    }

    /**
     * 获取订单列表(从我的订单接口进入)
     *
     * @param goodsList
     * @return
     * @author DK
     */
    @ResponseBody
    @RequestMapping(value = "getOrderGoodsList.do_", method = RequestMethod.POST)
    public Result<List<OrdOrderGoodsVo>> getOrderGdsList(String goodsList, Long orderId) {
        List<OrdOrderGoodsVo> orderGodsList = (List<OrdOrderGoodsVo>) JSONArray.toList(JSONArray.fromObject(goodsList), new OrdOrderGoodsVo(), new JsonConfig());
        if (CollectionUtils.isEmpty(orderGodsList)) {
            return Result.getSuccessResult(null);
        }
        List<OrdOrderGoodsVo> orderList = orderService.getOrderGoods(orderGodsList, orderId);
        if (CollectionUtils.isEmpty(orderList)) {
            return Result.getBusinessException("获取订单列表失败", "");
        }
        return Result.getSuccessResult(orderList);
    }

    /**
     * 加入购物车
     *
     * @param request
     * @param ordCartGoodsVo
     * @return
     * @author hhj
     */
    @ResponseBody
    @AccessLogin
    @RequestMapping("addCartgoods.do_")
    public Result addCartgds(HttpServletRequest request, OrdCartGoodsVo ordCartGoodsVo) {
        if (ordCartGoodsVo == null) {
            return Result.getBusinessException("选择后再添加", null);
        }
        if (ordCartGoodsVo.getNum()<= 0) {
            return Result.getBusinessException("商品数量不可以少于1个", null);
        }
        List<CartGoods> list;
        list = orderService.getCarGoodsById(getUid());
        GoodsDetailRE gdDTO = goodsService.getGoodsDetailByPropertyId(ordCartGoodsVo.getPropertyId());
        if (gdDTO.getStatus().longValue() == GoodsStatusEnum.GOODS_IS_SHELVES.getType().longValue() || gdDTO.getIsDelete().longValue() == GoodsStatusEnum.GOODS_DELETE.getType().longValue()) {
            return Result.getBusinessException("商品已经下架不可加入！！", null);
        }
        if (gdDTO.getStock() < ordCartGoodsVo.getNum()) {
            return Result.getBusinessException("添加数量超过库存量", null);
        }
        if (!CollectionUtils.isEmpty(list)) {
            for (CartGoods cartGds : list) {
                //判断该商品是否存在
                if (cartGds.getGoodsPropertyId().equals(ordCartGoodsVo.getPropertyId())) {
                    //如果存在增加数量
                    ordCartGoodsVo.setNum(ordCartGoodsVo.getNum() + cartGds.getGoodsNum());
                    ordCartGoodsVo.setMemberId(getUid());
                    try {
                        orderService.updataCarGoodsNum(ordCartGoodsVo);
                        return Result.getSuccessResult(null);
                    } catch (Exception e) {
                        logger.error("添加异常", e);
                        return Result.getBusinessException("修改异常", null);
                    }
                }
            }
        }
        ordCartGoodsVo.setMemberId(getUid());
        try {
            ordCartGoodsVo.setMemberId(getUid());
            orderService.addCarGoods(ordCartGoodsVo);
        } catch (Exception e) {
            logger.error("添加异常", e);
            return Result.getBusinessException("添加异常", null);
        }
        return Result.getSuccessResult(null);
    }

    /**
     * 删除购物车
     *
     * @param request
     * @param ordCartGoodsVo
     * @return
     * @author hhj
     */
    @ResponseBody
    @RequestMapping(value = "deleteCartgoods.do_", method = RequestMethod.POST)
    public Result deleteCartgds(HttpServletRequest request, OrdCartGoodsVo ordCartGoodsVo) {
        try {
            ordCartGoodsVo.setMemberId(getUid());
            orderService.deleteCarGoods(ordCartGoodsVo);
        } catch (Exception e) {
            logger.error("删除异常", e);
            return Result.getBusinessException("删除异常", null);
        }
        return Result.getSuccessResult(null);
    }

    /**
     * 提交订单信息
     *
     * @param ordOrderGoodsVo
     * @return
     */
    @ResponseBody
    @AccessLogin
    @RequestMapping(value = "confirmOrderInfo.do_", method = RequestMethod.POST)
    public Result confirmOrderInfo(OrdOrderGoodsVo ordOrderGoodsVo) {
        List<OrdOrderGoodsVo> orderInfoListNow = ordOrderGoodsVo.getList();
        if (CollectionUtils.isEmpty(orderInfoListNow)) {
            return Result.getSuccessResult("提交订单失败");
        }
        int a = 2;
        orderInfoListNow.get(orderInfoListNow.size() - a).setMemberId(getUid());
        List<OrderConfirmRE> orderConfirmInfo = orderService.confirmOrderInfo(orderInfoListNow);
        if (CollectionUtils.isEmpty(orderConfirmInfo)) {
            return Result.getBusinessException("提交订单失败", null);
        }
        return Result.getSuccessResult(orderConfirmInfo);
    }

    /**
     * 获取订单商品详情
     *
     * @param id(订单id)
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getOrderGoods.do_", method = RequestMethod.POST)
    public Result getOrderGoods(Integer id) {
        Result result = new Result();
        List<OrderGoodsDetailRe> list;
        list = orderService.getOrderGdsById(id);
        result.setRe(list);
        return result;
    }

    /**
     * 查询用户余额是否满足订单所需金额
     *
     * @param orderPayInfo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "queryBalances.do_", method = RequestMethod.POST)
    public Result queryBalances(String orderPayInfo) {
        List<MemberInfoVO> orderPayInfoNow = (List<MemberInfoVO>) JSONArray.toList(JSONArray.fromObject(orderPayInfo), new MemberInfoVO(), new JsonConfig());
        if (CollectionUtils.isEmpty(orderPayInfoNow)) {
            return Result.getBusinessException("支付失败", null);
        }
        orderPayInfoNow.get(0).setMemberId(getUid());
        // 支付订单前判断该用户订单状态是否为待付款状态
        OrdMemberVO ordMemberVO = new OrdMemberVO();
        ordMemberVO.setMemberId(orderPayInfoNow.get(0).getMemberId());
        ordMemberVO.setOrderId(orderPayInfoNow.get(0).getOrderId());
        List<Order> order = orderService.getOrderByMemberVO(ordMemberVO);
        if (!CollectionUtils.isEmpty(order)) {
            if (order.get(0).getStatus() != 1 || !order.get(0).getMemberId().equals(getUid())) {
                return Result.getSuccessResult("支付失败");
            }
        }

        //生成消息体
        MqVO mqVO = new MqVO();
        GenerateSmsVO generateSmsVO = new GenerateSmsVO();
        ordMemberVO = new OrdMemberVO();
        ordMemberVO.setOrderId(orderPayInfoNow.get(0).getOrderId());
        ordMemberVO.setMemberId(getUid());
        List<Order> ord = orderService.getOrderByMemberVO(ordMemberVO);
        if (ord.size() != 1) {
            return Result.getBusinessException("该订单不存在！", null);
        }
        //根据订单号获取手机号
        generateSmsVO.setTelephone(ord.get(0).getReceiptTel());
        generateSmsVO.setMessage(ord.get(0).getOrderNum());
        generateSmsVO.setCode(SmsTypeEnum.ORDER_INFO.getCode());
        generateSmsVO.setType(SmsTypeEnum.ORDER_INFO.getType());
        mqVO.setGenerateSmsVO(generateSmsVO);

        List<OrderConfirmRE> list = memberService.queryBalances(orderPayInfoNow, mqVO);
        if (CollectionUtils.isEmpty(list)) {
            return Result.getBusinessException("支付失败", null);
        }
        return Result.getSuccessResult(list);
    }

    /**
     * 去结算校验库存
     *
     * @param goodsList
     * @return
     * @author hhj
     */
    @ResponseBody
    @RequestMapping(value = "checkStock.do_", method = RequestMethod.POST)
    public Result checkStock(String[] goodsList) {
        OrdGoodsVO ordGoodsVO = new OrdGoodsVO();
        List<Long> listId = new ArrayList<>();
        if (goodsList != null) {
            for (String s : goodsList) {
                listId.add(Long.parseLong(s));
            }
        } else {
            return Result.getBusinessException("请选择商品再提交", null);
        }
        ordGoodsVO.setMemberId(getUid());
        ordGoodsVO.setList(listId);
        List<CartGoods> cartList = orderService.getCarGoodsByIds(ordGoodsVO);
        GoodsDetailRE gdDTO;
        if (CollectionUtils.isEmpty(cartList)) {
            return Result.getBusinessException("您选择的商品已丢失，请重新到商品页面添加！！", null);
        } else {
            for (CartGoods cargd : cartList) {
                if (cargd.getGoodsNum()<=0) {
                    return Result.getBusinessException("所选商品不可以小于1一个!", null);
                }
                gdDTO = goodsService.getGoodsDetailByPropertyId(cargd.getGoodsPropertyId());
                if (gdDTO == null) {
                    return Result.getBusinessException("选择的商品已找不到信息请刷新，请重新到商品页面添加", null);
                }
                if (gdDTO.getStock() < cargd.getGoodsNum()
                        || gdDTO.getStatus().equals(GoodsStatusEnum.GOODS_IS_SHELVES.getType().longValue())
                        || gdDTO.getIsDelete().equals(GoodsStatusEnum.GOODS_DELETE.getType().longValue())) {
                    return Result.getBusinessException("选择的商品：“ " + gdDTO.getName() + " ” 库存不足或已下架或被删除无法购买，请取消选择", null);
                }
            }
        }
        return Result.getSuccessResult(null);
    }

    /**
     * 获取用户订单信息列表
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getOrderInfoList.do_", method = RequestMethod.POST)
    public Result getOrderInfoList(OrdMemberVO ordMemberVO) {
        ordMemberVO.setMemberId(getUid());
        List<OrderInfoRE> orderInfoREList = orderService.getOrderInfoListByMemberId(ordMemberVO);
        if (CollectionUtils.isEmpty(orderInfoREList)) {
            return Result.getSuccessResult(null);
        }
        return Result.getSuccessResult(orderInfoREList);
    }

    /**
     * 取消订单
     *
     * @param ordOrderVo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "cancelOrder.do_", method = RequestMethod.POST)
    public Result cancelOrder(OrdOrderVo ordOrderVo) {
        ordOrderVo.setStatus(OrderEnum.CANCEL.getKey().longValue());
        ordOrderVo.setMemberId(getUid());

        if (orderService.updateOrder(ordOrderVo) > 0) {
            return Result.getSuccessResult(null);
        }
        return Result.getBusinessException("取消失败", null);
    }

    /**
     * 删除订单
     *
     * @param ordOrderVo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "goDelete.do_", method = RequestMethod.POST)
    public Result goDelete(OrdOrderVo ordOrderVo) {
        ordOrderVo.setMemberId(getUid());
        if (orderDao.memberDelOrder(ordOrderVo) > 0) {
            return Result.getSuccessResult(null);
        }
        return Result.getBusinessException("删除失败", null);
    }
}
