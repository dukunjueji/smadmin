package com.uc.training.smadmin.ord.controller;

import com.uc.training.common.annotation.AccessLogin;
import com.uc.training.common.base.controller.BaseController;
import com.uc.training.smadmin.bd.service.MemberService;
import com.uc.training.smadmin.gds.service.GoodsService;
import com.uc.training.smadmin.ord.re.OrderGoodsDetailRe;
import com.uc.training.smadmin.ord.re.OrderRe;
import com.uc.training.smadmin.ord.re.OrderStatusRe;
import com.uc.training.smadmin.ord.service.OrderService;
import com.uc.training.smadmin.ord.vo.OrdOrderGoodsVo;
import org.apache.commons.lang.StringUtils;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单控制类
 *
 * @author hhj
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
   *@author hhj
   * @param orderVo
   * @return
   */
  @ResponseBody
  @AccessLogin
  @RequestMapping(value = "getOrderPage.do_", method = RequestMethod.POST)
  public Result getOrderPage(OrdOrderVo orderVo) {
    List<OrderRe> list;
    Map map = new HashMap(10);
    list = orderService.getOrderPage(orderVo);
    if(CollectionUtils.isEmpty(list)){
      return Result.getBusinessException("获取订单列表失败",null);
    }
    map.put("orderList", list);
    Integer totalSize = orderService.getOrderTotal(orderVo);
    map.put("totalSize", totalSize);
    return Result.getSuccessResult(map);
  }

  /**
   * 批量删除订单（更改删除/进度状态）
   *@author hhj
   * @param delId（orderVo.getOrderListStr()）
   * @return
   */
  @ResponseBody
  @AccessLogin
  @RequestMapping(value = "admDelOrder.do_", method = RequestMethod.POST)
  public Result delOrderPage(String delId) {
    Result result = new Result();
    if (StringUtils.isEmpty(delId)) {
      return Result.getBusinessException("删除失败",null);
    }
    //截取字符串
    String[] deleteId = StringUtils.split(delId.substring(1, delId.length()-1), ',');
    List<Long> list = new ArrayList<>();
    //判空
    if (deleteId.length > 1) {
      for (String s : deleteId) {
        list.add(Long.parseLong(s));
      }
    } else {
      return Result.getBusinessException("删除失败",null);
    }
    int num = orderService.logicDelOrder(list);
    if (num > 0) {
      return Result.getSuccessResult(null);
    }
    return Result.getBusinessException("删除失败",null);
  }

  /**
   * 获取订单商品详情
   *@author hhj
   * @param id(订单id)
   * @return
   */
  @ResponseBody
  @AccessLogin
  @RequestMapping(value = "getOrderGoods.do_", method = RequestMethod.POST)
  public Result getOrderGoods(Integer id) {
    List<OrderGoodsDetailRe> list;
    try {
      list = orderService.getOrderGdsById(id);
    } catch (Exception e) {
      return Result.getBusinessException("获取异常",null);
    }
    return Result.getSuccessResult(list);
  }


  /**
   * 根据订单号更新状态
   *@author hhj
   * @param ordOrderVo
   * @return
   */
  @ResponseBody
  @AccessLogin
  @RequestMapping(value = "upOrderStatus.do_", method = RequestMethod.POST)
  public Result upOrderStatus(OrdOrderVo ordOrderVo) {
    Result result = new Result();
    if (orderService.updateOrder(ordOrderVo) > 0) {
      return Result.getSuccessResult(null);
    }
    return Result.getBusinessException("更新失败",null);
  }
}
