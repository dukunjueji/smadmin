package com.uc.training.ord.controller;

import com.uc.training.base.bd.re.MemberRE;
import com.uc.training.base.bd.service.MemberService;
import com.uc.training.base.bd.vo.MemberInfoVO;
import com.uc.training.base.bd.vo.MemberVO;
import com.uc.training.base.sms.vo.GenerateSmsVO;
import com.uc.training.common.annotation.AccessLogin;
import com.uc.training.common.base.controller.BaseController;
import com.uc.training.common.enums.GoodsStatusEnum;
import com.uc.training.common.enums.OrderEnum;
import com.uc.training.common.enums.OrderGoodsCommentEnum;
import com.uc.training.common.enums.SmsTypeEnum;
import com.uc.training.common.mq.vo.MqVO;
import com.uc.training.gds.re.GoodsDetailRE;
import com.uc.training.gds.service.GoodsService;
import com.uc.training.ord.re.CartGoodsRE;
import com.uc.training.ord.re.CommentRE;
import com.uc.training.ord.re.OrdOrderGoodsRE;
import com.uc.training.ord.re.OrderConfirmRE;
import com.uc.training.ord.re.OrderGoodsDetailRE;
import com.uc.training.ord.re.OrderInfoRE;
import com.uc.training.ord.re.OrderRE;
import com.uc.training.ord.service.OrderService;
import com.uc.training.ord.vo.OrdCartGoodsVO;
import com.uc.training.ord.vo.OrdGoodsVO;
import com.uc.training.ord.vo.OrdMemberVO;
import com.uc.training.ord.vo.OrdOrderGoodsVO;
import com.uc.training.ord.vo.OrdOrderVO;
import com.uc.training.remote.client.OrderClient;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private OrderService orderService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private MemberService memberService;

    /**
     * 获取用户购物车商品列表
     *
     * @return
     * @author hhj
     */
    @ResponseBody
    @RequestMapping(value = "getCartList.do_", method = RequestMethod.GET)
    public Result getCartgds() {
        List<OrdCartGoodsVO> list = new ArrayList<>();
        List<CartGoodsRE> cartList = orderService.getCarGoodsById(getUid());
        //判空
        if (CollectionUtils.isEmpty(cartList)) {
            return Result.getSuccessResult(null);
        }
        OrdCartGoodsVO ordCartgoodsVO;
        GoodsDetailRE gdDTO;
        for (CartGoodsRE cartGoods : cartList) {
            ordCartgoodsVO = new OrdCartGoodsVO();
            gdDTO = goodsService.getGoodsDetailByPropertyId(cartGoods.getGoodsPropertyId());
            if (gdDTO != null) {
                ordCartgoodsVO.setCartId(cartGoods.getId());
                ordCartgoodsVO.setGoodsId(gdDTO.getGoodsId());
                ordCartgoodsVO.setGdsName(gdDTO.getName());
                if (!CollectionUtils.isEmpty(gdDTO.getPicUrl())) {
                    ordCartgoodsVO.setGdsUrl(gdDTO.getPicUrl().get(0).getPicUrl());
                }
                ordCartgoodsVO.setPropertyId(cartGoods.getGoodsPropertyId());
                ordCartgoodsVO.setProperty(gdDTO.getProperty());
                ordCartgoodsVO.setSalePrice(gdDTO.getSalePrice());
                ordCartgoodsVO.setDiscountPrice(gdDTO.getDiscountPrice());
                ordCartgoodsVO.setStatus(gdDTO.getStatus());
                ordCartgoodsVO.setIsDiscount(gdDTO.getIsDiscount());
                ordCartgoodsVO.setNum(cartGoods.getGoodsNum().longValue());
                ordCartgoodsVO.setStock(gdDTO.getStock());
                list.add(ordCartgoodsVO);
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
     * @param ordCartGoodsVO
     * @return
     * @author hhj
     */
    @ResponseBody
    @AccessLogin
    @RequestMapping(value = "updataCartgoods.do_", method = RequestMethod.POST)
    public Result updataCartgds(HttpServletRequest request, OrdCartGoodsVO ordCartGoodsVO) {
        ordCartGoodsVO.setMemberId(getUid());
        GoodsDetailRE gdDTO = goodsService.getGoodsDetailByPropertyId(ordCartGoodsVO.getPropertyId());
        //判空
        if (gdDTO == null) {
            return Result.getBusinessException("获取异常", null);
        }
        // 更改数据判断库存如果库存不足更新为当前库存量并返回当前库存数据
        if (gdDTO.getStock() <= ordCartGoodsVO.getNum()) {
            ordCartGoodsVO.setNum(gdDTO.getStock());
            orderService.updateCarGoodsNum(ordCartGoodsVO);
            return Result.getSuccessResult(gdDTO.getStock());
        }
        orderService.updateCarGoodsNum(ordCartGoodsVO);
        return Result.getSuccessResult(gdDTO.getStock());
    }

    /**
     * 获取订单列表
     *
     * @param id
     * @return
     * @author HQ
     */
    @ResponseBody
    @AccessLogin
    @RequestMapping(value = "getOrderDetailById.do_", method = RequestMethod.POST)
    public Result<List<OrderInfoRE>> getOrderDetailById(Long id) {
        if (id == null) {
            return Result.getBusinessException("请重新选择订单", null);
        }
        OrdMemberVO ordMemberVO = new OrdMemberVO();
        ordMemberVO.setMemberId(getUid());
        ordMemberVO.setOrderId(id);
        return Result.getSuccessResult(orderService.getOrderInfoListByMemberId(ordMemberVO));
    }
    /**
     * 获取提交订单的商品列表(从购物车接口进入)
     *
     * @param goodsList
     * @return
     * @author DK
     */
    @ResponseBody
    @RequestMapping(value = "getOrderList.do_", method = RequestMethod.GET)
    public Result<List<OrdOrderGoodsRE>> getOrderGds(String goodsList) {
        // 接受前臺提交的購物車列表商品 包含propertyId(商品屬性id)、goodsNum(商品數量)、goodsId(商品id)、订单Id(从我的订单页面进入)
        List<OrdOrderGoodsVO> orderGodsList = (List<OrdOrderGoodsVO>) JSONArray.toList(JSONArray.fromObject(goodsList), new OrdOrderGoodsVO(), new JsonConfig());
        if (CollectionUtils.isEmpty(orderGodsList)) {
            return Result.getSuccessResult(null);
        }
        orderGodsList.get(0).setMemberId(getUid());
        List<OrdOrderGoodsRE> orderList = orderService.getOrderGoodsById(orderGodsList);
        if (CollectionUtils.isEmpty(orderList)) {
            return Result.getBusinessException("获取订单列表失败", "");
        }
        return Result.getSuccessResult(orderList);
    }

    /**
     * 获取已经存在的订单商品列表(从我的订单接口进入)
     *
     * @param goodsList
     * @return
     * @author DK
     */
    @ResponseBody
    @RequestMapping(value = "getOrderGoodsList.do_", method = RequestMethod.POST)
    public Result<List<OrdOrderGoodsRE>> getOrderGdsList(String goodsList, Long orderId) {
        List<OrdOrderGoodsVO> list = (List<OrdOrderGoodsVO>) JSONArray.toList(JSONArray.fromObject(goodsList), new OrdOrderGoodsVO(), new JsonConfig());
        if (CollectionUtils.isEmpty(list)) {
            return Result.getSuccessResult(null);
        }
        List<OrdOrderGoodsRE> orderList = orderService.getOrderGoods(list, orderId);
        if (CollectionUtils.isEmpty(orderList)) {
            return Result.getBusinessException("获取订单列表失败", "");
        }
        return Result.getSuccessResult(orderList);
    }

    /**
     * 加入购物车
     *
     * @param request
     * @param ordCartGoodsVO
     * @return
     * @author hhj
     */
    @ResponseBody
    @AccessLogin
    @RequestMapping("addCartgoods.do_")
    public Result addCartgds(HttpServletRequest request, OrdCartGoodsVO ordCartGoodsVO) {
        if (ordCartGoodsVO == null) {
            return Result.getBusinessException("选择后再添加", null);
        }
        if (ordCartGoodsVO.getNum() <= 0) {
            return Result.getBusinessException("商品数量不可以少于1个", null);
        }
        List<CartGoodsRE> list;
        list = orderService.getCarGoodsById(getUid());
        GoodsDetailRE gdDTO = goodsService.getGoodsDetailByPropertyId(ordCartGoodsVO.getPropertyId());
        if (gdDTO.getStatus().longValue() == GoodsStatusEnum.GOODS_IS_SHELVES.getType().longValue() || gdDTO.getIsDelete().longValue() == GoodsStatusEnum.GOODS_DELETE.getType().longValue()) {
            return Result.getBusinessException("商品已经下架不可加入！！", null);
        }
        if (gdDTO.getStock() < ordCartGoodsVO.getNum()) {
            return Result.getBusinessException("添加数量超过库存量", null);
        }
        if (!CollectionUtils.isEmpty(list)) {
            for (CartGoodsRE cartGds : list) {
                //判断该商品是否存在
                if (cartGds.getGoodsPropertyId().equals(ordCartGoodsVO.getPropertyId())) {
                    //如果存在增加数量
                    ordCartGoodsVO.setNum(ordCartGoodsVO.getNum() + cartGds.getGoodsNum());
                    ordCartGoodsVO.setMemberId(getUid());
                    try {
                        orderService.updateCarGoodsNum(ordCartGoodsVO);
                        return Result.getSuccessResult(null);
                    } catch (Exception e) {
                        logger.error("添加异常", e);
                        return Result.getBusinessException("修改异常", null);
                    }
                }
            }
        }
        ordCartGoodsVO.setMemberId(getUid());
        try {
            ordCartGoodsVO.setMemberId(getUid());
            orderService.addCarGoods(ordCartGoodsVO);
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
     * @param ordCartGoodsVO
     * @return
     * @author hhj
     */
    @ResponseBody
    @RequestMapping(value = "deleteCartgoods.do_", method = RequestMethod.POST)
    public Result deleteCartgds(HttpServletRequest request, OrdCartGoodsVO ordCartGoodsVO) {
        try {
            ordCartGoodsVO.setMemberId(getUid());
            orderService.deleteCarGoods(ordCartGoodsVO);
        } catch (Exception e) {
            logger.error("删除异常", e);
            return Result.getBusinessException("删除异常", null);
        }
        return Result.getSuccessResult(null);
    }

    /**
     * 提交订单信息
     *
     * @param ordOrderGoodsVO
     * @return
     */
    @ResponseBody
    @AccessLogin
    @RequestMapping(value = "confirmOrderInfo.do_", method = RequestMethod.POST)
    public Result confirmOrderInfo(OrdOrderGoodsVO ordOrderGoodsVO) {
        List<OrdOrderGoodsVO> orderInfoListNow = ordOrderGoodsVO.getList();
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
        List<OrderGoodsDetailRE> list;
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
            return Result.getSuccessResult(null);
        }
        orderPayInfoNow.get(0).setMemberId(getUid());
        // 支付订单前判断该用户订单状态是否为待付款状态
        OrdMemberVO ordMemberVO = new OrdMemberVO();
        ordMemberVO.setMemberId(orderPayInfoNow.get(0).getMemberId());
        ordMemberVO.setOrderId(orderPayInfoNow.get(0).getOrderId());
        List<OrderRE> order = orderService.getOrderByMemberVO(ordMemberVO);
        if (!CollectionUtils.isEmpty(order)) {
            if (order.get(0).getStatus() != 1 || !order.get(0).getMemberId().equals(getUid())) {
                return Result.getSuccessResult("支付失败");
            }
        }
        //生成消息体
        MqVO mqVO = new MqVO();
        GenerateSmsVO generateSmsVO = new GenerateSmsVO();
        MemberVO memberVO = new MemberVO();
        memberVO.setId(getUid());
        MemberRE memberRE = memberService.queryOneMember(memberVO);
        //根据订单号获取手机号
        generateSmsVO.setTelephone(order.get(0).getReceiptTel());
        generateSmsVO.setMessage(order.get(0).getOrderNum());
        generateSmsVO.setCode(SmsTypeEnum.ORDER_INFO.getCode());
        generateSmsVO.setType(SmsTypeEnum.ORDER_INFO.getType());
        generateSmsVO.setEmil(memberRE.getEmail());
        generateSmsVO.setEmil(order.get(0).getTeag());
        mqVO.setGenerateSmsVO(generateSmsVO);
        List<OrderConfirmRE> list = memberService.queryBalances(orderPayInfoNow, mqVO);
        if (CollectionUtils.isEmpty(list)) {
            return Result.getSuccessResult(null);
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
        List<CartGoodsRE> cartList = orderService.getCarGoodsByIds(ordGoodsVO);
        GoodsDetailRE gdDTO;
        if (CollectionUtils.isEmpty(cartList)) {
            return Result.getBusinessException("您选择的商品已丢失，请重新到商品页面添加！！", null);
        } else {
            for (CartGoodsRE cargd : cartList) {
                if (cargd.getGoodsNum() <= 0) {
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
     * @param ordOrderVO
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "cancelOrder.do_", method = RequestMethod.POST)
    public Result cancelOrder(OrdOrderVO ordOrderVO) {
        ordOrderVO.setStatus(OrderEnum.CANCEL.getKey().longValue());
        ordOrderVO.setMemberId(getUid());
        if (orderService.updateOrder(ordOrderVO) > 0) {
            return Result.getSuccessResult(null);
        }
        return Result.getBusinessException("取消失败", null);
    }

    /**
     * 确认收货
     *
     * @param ordOrderVO
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "confirmGoods.do_", method = RequestMethod.POST)
    public Result confirmGoods(OrdOrderVO ordOrderVO) {
        ordOrderVO.setStatus(OrderEnum.COMPLETED.getKey().longValue());
        ordOrderVO.setMemberId(getUid());
        if (orderService.updateOrder(ordOrderVO) > 0) {
            return Result.getSuccessResult(null);
        }
        return Result.getBusinessException("确认收货失败", null);
    }

    /**
     * 删除订单
     *
     * @param ordOrderVO
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "goDelete.do_", method = RequestMethod.POST)
    public Result goDelete(OrdOrderVO ordOrderVO) {
        ordOrderVO.setMemberId(getUid());
        if (OrderClient.memberDelOrder(ordOrderVO) > 0) {
            return Result.getSuccessResult(null);
        }
        return Result.getBusinessException("删除失败", null);
    }

    /**
     * 查询购物车商品数量
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getCartgoodscount.do_", method = RequestMethod.GET)
    public Result getCartgoodsCount() {
        return Result.getSuccessResult(orderService.queryCartGoodsCount(getUid()));
    }

    /**
     * 查询未评价商品详情
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getCommentGoodsDetail.do_", method = RequestMethod.GET)
    public Result getCommentGoodsDetail(OrdGoodsVO ordGoodsVO) {
        ordGoodsVO.setMemberId(getUid());
        List<CommentRE> list = orderService.getCommentOrderGoodsDetail(ordGoodsVO);
        return Result.getSuccessResult(list);
    }

    /**
     * 查询未评价商品数量
     *
     * @param ordGoodsVO
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getCommentGoodsCount.do_", method = RequestMethod.GET)
    public Result getCommentGoodsCount(OrdGoodsVO ordGoodsVO) {
        ordGoodsVO.setMemberId(getUid());
        ordGoodsVO.setCommentStatus(OrderGoodsCommentEnum.NO_COMMENT.getKey());
        return Result.getSuccessResult(orderService.getCommentGoodsCount(ordGoodsVO));
    }

}
