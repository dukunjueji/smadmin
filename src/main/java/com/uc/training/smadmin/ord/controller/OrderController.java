package com.uc.training.smadmin.ord.controller;

import com.uc.training.common.annotation.AccessLogin;
import com.uc.training.common.base.controller.BaseController;
import com.uc.training.common.enums.OrderEnum;
import com.uc.training.common.enums.SmsTypeEnum;
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
import com.uc.training.smadmin.ord.vo.*;
import com.uc.training.smadmin.sms.vo.GenerateSmsVO;
import com.ycc.base.common.Result;
import com.uc.training.common.base.controller.BaseController;
import com.uc.training.smadmin.gds.service.GoodsService;
import com.uc.training.smadmin.ord.re.OrderRe;
import com.uc.training.smadmin.ord.re.OrderStatusRe;
import com.uc.training.smadmin.ord.service.OrderService;
import com.ycc.base.common.Result;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.apache.commons.collections.OrderedMap;
import org.apache.commons.lang.StringUtils;
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
   *@author hhj
   * @return
   */
  @ResponseBody
  @AccessLogin
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
        ordCartgoodsVo.setGoodsId(cartGoods.getGoodsId());
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
        ordCartgoodsVo.setNum(cartGoods.getGoodsNum());
        ordCartgoodsVo.setStock(gdDTO.getStock());
        list.add(ordCartgoodsVo);
      } else {
        return Result.getBusinessException("获取异常",null);
      }
    }
    return Result.getSuccessResult(list);
  }

  /**
   * 更改购物车信息（更改商品数量）
   * @author hhj
   * @param request
   * @param ordCartGoodsVo
   * @return
   */
  @ResponseBody
  @AccessLogin
  @RequestMapping(value = "updataCartgoods.do_", method = RequestMethod.POST)
  public Result updataCartgds(HttpServletRequest request, OrdCartGoodsVo ordCartGoodsVo) {
    ordCartGoodsVo.setMemberId(getUid());
    GoodsDetailRE gdDTO = goodsService.getGoodsDetailByPropertyId(ordCartGoodsVo.getPropertyId());
    //判空
    if(gdDTO == null){
      return Result.getBusinessException("获取异常",null);
    }
    // 更改数据判断库存如果库存不足更新为当前库存量并返回当前库存数据
    if(gdDTO.getStock() <= ordCartGoodsVo.getNum()){
      ordCartGoodsVo.setNum(gdDTO.getStock());
      orderService.updataCarGoodsNum(ordCartGoodsVo);
      return Result.getSuccessResult(gdDTO.getStock());
    }
    orderService.updataCarGoodsNum(ordCartGoodsVo);
    return Result.getSuccessResult(gdDTO.getStock());
  }

    /**
     * 获取订单列表
     *@author DK
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
   *
   * 加入购物车
   * @author hhj
   * @param request
   * @param ordCartGoodsVo
   * @return
   */
  @ResponseBody
  @AccessLogin
  @RequestMapping("addCartgoods.do_")
  public Result addCartgds(HttpServletRequest request, OrdCartGoodsVo ordCartGoodsVo) {
    if(ordCartGoodsVo == null){
      return Result.getBusinessException("选择后再添加",null);
    }
    List<CartGoods> list;
    list = orderService.getCarGoodsById(getUid());
    GoodsDetailRE gdDTO = goodsService.getGoodsDetailByPropertyId(ordCartGoodsVo.getPropertyId());
    if(gdDTO.getStock() < ordCartGoodsVo.getNum()){
      return Result.getBusinessException("添加数量超过库存量",null);
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
            return Result.getBusinessException("修改异常",null);
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
      return Result.getBusinessException("添加异常",null);
    }
    return Result.getSuccessResult(null);
  }

  /**
   * 删除购物车
   * @author hhj
   * @param request
   * @param ordCartGoodsVo
   * @return
   */
  @ResponseBody
  @AccessLogin
  @RequestMapping(value = "deleteCartgoods.do_", method = RequestMethod.POST)
  public Result deleteCartgds(HttpServletRequest request, OrdCartGoodsVo ordCartGoodsVo) {
    try {
      orderService.deleteCarGoods(ordCartGoodsVo);
    } catch (Exception e) {
      logger.error("删除异常", e);
      return Result.getBusinessException("删除异常",null);
    }
    return Result.getSuccessResult(null);
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
     * 去结算校验库存
     *@author hhj
     * @param goodsList
     * @return
     */
    @ResponseBody
    @AccessLogin
    @RequestMapping(value = "checkStock.do_", method = RequestMethod.POST)
    public Result checkStock(String goodsList) {
      List<OrdGoodsVO> list = (List<OrdGoodsVO>) JSONArray.toList(JSONArray.fromObject(goodsList), new OrdGoodsVO(), new JsonConfig());
      GoodsDetailRE gdDTO;
      if (CollectionUtils.isEmpty(list)) {
        return Result.getBusinessException("请选择商品再提交",null);
      } else {
        for (OrdGoodsVO cargd:list ) {
          gdDTO = goodsService.getGoodsDetailByPropertyId(cargd.getPropertyId());
          if(gdDTO == null){
            return Result.getBusinessException("选择的商品中出现不存在请刷新",null);
          }
          if(gdDTO.getStock()<cargd.getGoodsNum() || gdDTO.getStatus() ==0 ){
            return Result.getBusinessException("选择的商品中存在库存不足问题",null);
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
            return Result.getBusinessException("获取用户订单信息失败", null);
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
        if (orderService.updateOrder(ordOrderVo) > 0) {
            return Result.getSuccessResult(null);
        }
        return Result.getBusinessException("取消失败", null);
    }
}
