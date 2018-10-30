package com.uc.training.smadmin.ord.controller;

import com.uc.training.common.annotation.AccessLogin;
import com.uc.training.common.base.controller.BaseController;
import com.uc.training.smadmin.bd.service.MemberService;
import com.uc.training.smadmin.bd.vo.MemberInfoVO;
import com.uc.training.smadmin.gds.re.GoodsDetailRE;
import com.uc.training.smadmin.gds.service.GoodsService;
import com.uc.training.smadmin.ord.model.CartGoods;
import com.uc.training.smadmin.ord.re.OrderConfirmRE;
import com.uc.training.smadmin.ord.model.Order;
import com.uc.training.smadmin.ord.re.*;
import com.uc.training.smadmin.ord.re.OrderGoodsDetailRe;
import com.uc.training.smadmin.ord.re.OrderRe;
import com.ycc.base.common.Result;
import com.uc.training.common.base.controller.BaseController;
import com.uc.training.smadmin.gds.service.GoodsService;
import com.uc.training.smadmin.ord.re.OrderRe;
import com.uc.training.smadmin.ord.re.OrderStatusRe;
import com.uc.training.smadmin.ord.service.OrderService;
import com.uc.training.smadmin.ord.vo.OrdCartGoodsVo;
import com.uc.training.smadmin.ord.vo.OrdOrderGoodsVo;
import com.uc.training.smadmin.ord.vo.OrdOrderVo;
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


    /**
     * 获取购物车用户商品列表
     * hhj
     *
     * @return
     */
    @ResponseBody
    @AccessLogin
    @RequestMapping(value = "getCartList.do_", method = RequestMethod.GET)
    public Result getCartgds() {
        Result result = new Result();
        List<OrdCartGoodsVo> list = new ArrayList<>();
        List<CartGoods> cartList = orderService.getCarGoodsById(getUid());
        //判空
        if (CollectionUtils.isEmpty(cartList)) {
            return result;
        }
        OrdCartGoodsVo ordCartgoodsVo;
        GoodsDetailRE gdDTO;
        for (CartGoods cartGoods : cartList) {
            ordCartgoodsVo = new OrdCartGoodsVo();
            gdDTO = goodsService.getGoodsDetailByPropertyId(cartGoods.getGoodsPropertyId());
            ordCartgoodsVo.setCartId(cartGoods.getId());
            ordCartgoodsVo.setGoodsId(cartGoods.getGoodsId());
            ordCartgoodsVo.setGdsName(gdDTO.getName());
            ordCartgoodsVo.setGdsUrl(gdDTO.getPicUrl().get(0).getPicUrl());
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
        result.setRe(list);
        return result;
    }

    /**
     * 更改购物车信息（更改商品数量）
     * hhj
     *
     * @param request
     * @param ordCartGoodsVo
     * @return
     */
    @ResponseBody
    @AccessLogin
    @RequestMapping(value = "updataCartgoods.do_", method = RequestMethod.POST)
    public Result updataCartgds(HttpServletRequest request, OrdCartGoodsVo ordCartGoodsVo) {
        Result result = new Result();
        ordCartGoodsVo.setMemberId(getUid());
        orderService.updataCarGoodsNum(ordCartGoodsVo);
        return result;
    }

    /**
     * 获取订单列表
     *
     * @param goodsList
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getOrderList.do_", method = RequestMethod.GET)
    public Result<List<OrdOrderGoodsVo>> getOrderGds(String goodsList) {
        List<OrdOrderGoodsVo> orderGodsList = (List<OrdOrderGoodsVo>) JSONArray.toList(JSONArray.fromObject(goodsList), new OrdOrderGoodsVo(), new JsonConfig());
        if (CollectionUtils.isEmpty(orderGodsList)) {
            return Result.getBusinessException("获取订单列表失败", "");
        }
        List<OrdOrderGoodsVo> orderList = orderService.getOrderGoodsById(orderGodsList);
        if (CollectionUtils.isEmpty(orderList)) {
            return Result.getBusinessException("获取订单列表失败", "");
        }
        return Result.getSuccessResult(orderList);
    }

    /**
     * hhj
     * 加入购物车
     *
     * @param request
     * @param ordCartGoodsVo
     * @return
     */
    @ResponseBody
    @AccessLogin
    @RequestMapping("addCartgoods.do_")
    public Result addCartgds(HttpServletRequest request, OrdCartGoodsVo ordCartGoodsVo) {
        Result result = new Result();
        List<CartGoods> list;
        list = orderService.getCarGoodsById(getUid());
        if (!CollectionUtils.isEmpty(list)) {
            for (CartGoods cartGds : list) {
                //判断该商品是否存在
                if (cartGds.getGoodsPropertyId().equals(ordCartGoodsVo.getPropertyId())) {
                    //如果存在增加数量
                    ordCartGoodsVo.setNum(ordCartGoodsVo.getNum() + cartGds.getGoodsNum());
                    ordCartGoodsVo.setMemberId(getUid());
                    orderService.updataCarGoodsNum(ordCartGoodsVo);
                    return result;
                }
            }
        }
        ordCartGoodsVo.setMemberId(getUid());
        orderService.addCarGoods(ordCartGoodsVo);
        return result;
    }

    /**
     * 删除购物车
     * hhj
     *
     * @param request
     * @param ordCartGoodsVo
     * @return
     */
    @ResponseBody
    @AccessLogin
    @RequestMapping(value = "deleteCartgoods.do_", method = RequestMethod.POST)
    public Result deleteCartgds(HttpServletRequest request, OrdCartGoodsVo ordCartGoodsVo) {
        Result result = new Result();
        ordCartGoodsVo.setMemberId(getUid());
        orderService.deleteCarGoods(ordCartGoodsVo);
        return result;
    }


    /**
     * 提交订单信息
     *
     * @param orderInfoList
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "confirmOrderInfo.do_", method = RequestMethod.POST)
    public Result confirmOrderInfo(String orderInfoList) {
        // 接受前台传来的list，其中list最后一个为订单的总价，最后第二个为用户地址信息，前面为所购商品信息
        List<OrdOrderGoodsVo> orderInfoListNow = (List<OrdOrderGoodsVo>) JSONArray.toList(JSONArray.fromObject(orderInfoList), new OrdOrderGoodsVo(), new JsonConfig());
        if (CollectionUtils.isEmpty(orderInfoListNow)) {
            return Result.getBusinessException("提交订单失败", null);
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
     * 批量删除订单（更改删除/进度状态）
     *
     * @param orderVo（orderVo.getOrderListStr()）
     * @return
     */
    @ResponseBody
    @AccessLogin
    @RequestMapping(value = "admDelOrder.do_", method = RequestMethod.POST)
    public Result delOrderPage(OrdOrderVo orderVo) {
        Result result = new Result();
        List<OrderRe> delList = (List<OrderRe>) JSONArray.toList(JSONArray.fromObject(orderVo.getOrderListStr()), new OrderRe(), new JsonConfig());
        int num = orderService.logicDelOrder(delList);
        if (num > 0) {
            result.setMsg("删除： " + num + "条信息");
        }
        return result;
    }

    /**
     * 获取订单商品详情
     *
     * @param id(订单id)
     * @return
     */
    @ResponseBody
    @AccessLogin
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
        List<OrderConfirmRE> list = memberService.queryBalances(orderPayInfoNow);
        if (CollectionUtils.isEmpty(list)) {
            return Result.getBusinessException("支付失败", null);
        }
        return Result.getSuccessResult(list);
    }

    /**
     * 根据订单号更新状态
     *
     * @param ordOrderVo
     * @return
     */
    @ResponseBody
    @AccessLogin
    @RequestMapping(value = "upOrderStatus.do_", method = RequestMethod.POST)
    public Result upOrderStatus(OrdOrderVo ordOrderVo) {
        Result result = new Result();
        if (orderService.updateOrder(ordOrderVo) > 0) {
            result.setMsg("已更新状态");
        }
        return result;
    }

    /**
     * 获取用户订单信息列表
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getOrderInfoList.do_", method = RequestMethod.POST)
    public Result getOrderInfoList() {
        List<OrderInfoRE> orderInfoREList = orderService.getOrderInfoListByMemberId(getUid());
        return Result.getSuccessResult(orderInfoREList);
    }
}
