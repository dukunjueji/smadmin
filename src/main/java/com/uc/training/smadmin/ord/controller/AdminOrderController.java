package com.uc.training.smadmin.ord.controller;

import com.uc.training.common.annotation.AccessLogin;
import com.uc.training.common.base.controller.BaseController;
import com.uc.training.smadmin.bd.service.MemberService;
import com.uc.training.smadmin.bd.vo.MemberInfoVO;
import com.uc.training.smadmin.gds.service.GoodsService;
import com.uc.training.smadmin.ord.re.OrderConfirmRE;
import com.uc.training.smadmin.ord.re.OrderGoodsDetailRe;
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
@RequestMapping(value = "admin/order/")
public class AdminOrderController extends BaseController {
  @Autowired
  OrderService orderService;

  @Autowired
  GoodsService goodsService;

  @Autowired
  MemberService memberService;
  /**
   * 获取订单分页
   *
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
    map.put("statuaList", statuaList);
    result.setRe(map);
    return result;
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
}
