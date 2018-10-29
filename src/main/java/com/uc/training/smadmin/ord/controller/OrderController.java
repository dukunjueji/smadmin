package com.uc.training.smadmin.ord.controller;

import com.uc.training.common.annotation.AccessLogin;
import com.uc.training.smadmin.bd.model.Member;
import com.uc.training.smadmin.bd.service.MemberService;
import com.uc.training.smadmin.bd.vo.MemberInfoVO;
import com.uc.training.smadmin.ord.model.Order;
import com.uc.training.smadmin.ord.re.OrderGoodsDetailRe;
import com.uc.training.smadmin.ord.re.OrderConfirmRE;
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
import org.stringtemplate.v4.ST;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

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
   * @return
   */
  @ResponseBody
  @AccessLogin
  @RequestMapping(value = "getCartList.do_", method = RequestMethod.GET)
  public Result getCartgds() {
    Result result = new Result();
    List<OrdCartGoodsVo> list;
    list = orderService.getCarGoodsById(getUid());
    OrdCartGoodsVo cartGoodsVo = new OrdCartGoodsVo();
    result.setRe(list);
    return result;
  }

  /**
   * 更改购物车信息（更改商品数量）
   * @param request
   * @param ordCartGoodsVo
   * @return
   */
  @ResponseBody
  @AccessLogin
  @RequestMapping(value = "updataCartgoods.do_", method = RequestMethod.POST)
  public Result updataCartgds(HttpServletRequest request, OrdCartGoodsVo ordCartGoodsVo) {
    Result result = new Result();
    //memberId = UID();
    ordCartGoodsVo.setMemberId(getUid());
    orderService.updataCarGoodsNum(ordCartGoodsVo);
    return result;
  }

    /**
     * 获取订单列表
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
        List<OrdOrderGoodsVo> orderList =orderService.getOrderGoodsById(orderGodsList);
        if (CollectionUtils.isEmpty(orderList)) {
            return Result.getBusinessException("获取订单列表失败", "");
        }
        return Result.getSuccessResult(orderList);
    }

  /**
   * 加入购物车
   * @param request
   * @param ordCartGoodsVo
   * @return
   */
  @ResponseBody
  @AccessLogin
  @RequestMapping("addCartgoods.do_")
  public Result addCartgds(HttpServletRequest request, OrdCartGoodsVo ordCartGoodsVo) {
    Result result = new Result();
    List<OrdCartGoodsVo> list;
    //memberId = UID();
    list = orderService.getCarGoodsById(getUid());
    for (OrdCartGoodsVo cartGdsList : list) {
      if (cartGdsList.getPropertyId().equals(ordCartGoodsVo.getPropertyId())) {
        result.setMsg("该商品在购物车已经存在！！");
        return result;
      }
    }
    //记得改为当前会员id
    ordCartGoodsVo.setMemberId(getUid());
    orderService.addCarGoods(ordCartGoodsVo);
    return result;
  }

  /**
   * 删除购物车
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
    ordCartGoodsVo.setMemberId(1L);
    orderService.deleteCarGoods(ordCartGoodsVo);
    return result;
  }


    /**
     * 提交订单信息
     * @param orderInfoList
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "confirmOrderInfo.do_", method = RequestMethod.POST)
    public Result confirmOrderInfo(String orderInfoList) {
        List<OrdOrderGoodsVo> orderInfoListNow = (List<OrdOrderGoodsVo>) JSONArray.toList(JSONArray.fromObject(orderInfoList), new OrdOrderGoodsVo(), new JsonConfig());
        if (CollectionUtils.isEmpty(orderInfoListNow)){
            return Result.getBusinessException("提交订单失败",null);
        }
        orderInfoListNow.get(orderInfoListNow.size() - 2).setMemberId(getUid());
        List<OrderConfirmRE> OrderConfirmInfo = orderService.confirmOrderInfo(orderInfoListNow);
        if (CollectionUtils.isEmpty(OrderConfirmInfo)) {
            return Result.getBusinessException("提交订单失败", null);
        }
        return Result.getSuccessResult(OrderConfirmInfo);
    }

  /**
   * 获取订单分页
   * @param orderVo
   * @return
   */
  @ResponseBody
  @AccessLogin
  @RequestMapping(value = "getOrderPage.do_", method = RequestMethod.POST)
  public Result getOrderPage(OrdOrderVo orderVo) {
    List<OrderStatusRe> statuaList;
    Result result = new Result();
    List<OrderRe> list;
    Map map = new HashMap();
    list = orderService.getOrderPage(orderVo);
    statuaList = orderService.getOrderEnum();
    map.put("orderList", list);
    Integer totalSize = orderService.getOrderTotal(orderVo);
    map.put("totalSize", totalSize);
    map.put("statuaList",statuaList);
    result.setRe(map);
    return result;
  }

  /**
   * 批量删除订单（更改删除/进度状态）
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
      result.setMsg("删除： "+num+"条信息");
    }
    return result;
  }

  /**
   * 获取订单商品详情
   * @param id
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
        List<OrderConfirmRE> list = memberService.queryBalances(orderPayInfoNow);
        if (CollectionUtils.isEmpty(list)) {
            return Result.getBusinessException("支付失败", null);
        }
        return Result.getSuccessResult(list);
    }

  /**
   * 根据订单号更新状态
   * @param ordOrderVo
   * @return
   */
  @ResponseBody
  @AccessLogin
  @RequestMapping(value = "upOrderStatus.do_",method = RequestMethod.POST)
  public Result upOrderStatus(OrdOrderVo ordOrderVo){
    Result result =new Result();
    if(orderService.updateOrder(ordOrderVo) > 0){
      result.setMsg("已更新状态");
    }
    return result;
  }
}
