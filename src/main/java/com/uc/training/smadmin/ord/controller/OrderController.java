package com.uc.training.smadmin.ord.controller;

import com.uc.training.common.annotation.AccessLogin;
import com.uc.training.smadmin.bd.model.Member;
import com.uc.training.smadmin.bd.service.MemberService;
import com.uc.training.smadmin.bd.vo.MemberInfoVO;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.stringtemplate.v4.ST;

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
  OrderService orderService;

  @Autowired
  GoodsService goodsService;

  @Autowired
  MemberService memberService;

  @ResponseBody
  @RequestMapping(value = "getCartList.do_", method = RequestMethod.GET)
  public Result getCartgds() {
    Result result = new Result();
    List<OrdCartGoodsVo> list;
    //memberId = UID();
    list = orderService.getCarGoodsById(1L);
    OrdCartGoodsVo cartGoodsVo = new OrdCartGoodsVo();
    result.setRe(list);
    return result;
  }

  @ResponseBody
  @RequestMapping(value = "updataCartgoods.do_", method = RequestMethod.POST)
  public Result updataCartgds(HttpServletRequest request, OrdCartGoodsVo ordCartGoodsVo) {
    Result result = new Result();
    //memberId = UID();
    ordCartGoodsVo.setMemberId(1L);
    orderService.updataCarGoodsNum(ordCartGoodsVo);
    return result;
  }

  /**
   * 获取订单
   * @param goodsList
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "getOrderList.do_", method = RequestMethod.GET)
  public Result<List<OrdOrderGoodsVo>> getOrderGds(String goodsList) {
    List<OrdOrderGoodsVo> orderGodsList = (List<OrdOrderGoodsVo>) JSONArray.toList(JSONArray.fromObject(goodsList), new OrdOrderGoodsVo(), new JsonConfig());
    Result<List<OrdOrderGoodsVo>> orderList = Result.getSuccessResult(orderService.getOrderGoodsById(orderGodsList));
    return orderList;
  }

  /**
   * 加入购物车
   * @param request
   * @param ordCartGoodsVo
   * @return
   */
  @ResponseBody
  @RequestMapping("addCartgoods.do_")
  public Result addCartgds(HttpServletRequest request, OrdCartGoodsVo ordCartGoodsVo) {
    Result result = new Result();
    List<OrdCartGoodsVo> list;
    //memberId = UID();
    list = orderService.getCarGoodsById(1L);
    for (OrdCartGoodsVo cartGdsList : list) {
      if (cartGdsList.getPropertyId().equals(ordCartGoodsVo.getPropertyId())) {
        result.setMsg("该商品在购物车已经存在！！");
        return result;
      }
    }
    //记得改为当前会员id
    ordCartGoodsVo.setMemberId(1L);
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
  @RequestMapping(value = "deleteCartgoods.do_", method = RequestMethod.POST)
  public Result deleteCartgds(HttpServletRequest request, OrdCartGoodsVo ordCartGoodsVo) {
    Result result = new Result();
    orderService.deleteCarGoods(ordCartGoodsVo);
    return result;
  }


    @ResponseBody
    @RequestMapping(value = "updateOrderInfo.do_", method = RequestMethod.POST)
    public Result updateOrderInfo(String orderInfoList) {
        Result result = new Result();
        List<OrdOrderGoodsVo> orderInfoListNow = (List<OrdOrderGoodsVo>) JSONArray.toList(JSONArray.fromObject(orderInfoList), new OrdOrderGoodsVo(), new JsonConfig());
        List<OrderRe> orderRe = orderService.updateOrderInfo(orderInfoListNow);
        result.setRe(orderRe);
        return result;
    }

  /**
   * 获取订单分页
   * @param orderVo
   * @return
   */
  @ResponseBody
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
   * 批量删除订单（更改状态）
   * @param orderVo（orderVo.getOrderListStr()）
   * @return
   */
  @ResponseBody
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
    @RequestMapping(value = "queryBalances.do_",method = RequestMethod.POST)
    public Result queryBalances(String orderPayInfo){
        List<MemberInfoVO> orderPayInfoNow = (List<MemberInfoVO>) JSONArray.toList(JSONArray.fromObject(orderPayInfo), new MemberInfoVO(), new JsonConfig());
        List<OrderRe> list= memberService.queryBalances(orderPayInfoNow);
        Result result =new Result();
        result.setRe(list);
        return result;
    }

}
