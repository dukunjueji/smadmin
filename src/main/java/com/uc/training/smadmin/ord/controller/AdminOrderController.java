package com.uc.training.smadmin.ord.controller;

import com.uc.training.common.annotation.AccessLogin;
import com.uc.training.common.base.controller.BaseController;
import com.uc.training.smadmin.bd.service.MemberService;
import com.uc.training.smadmin.gds.service.GoodsService;
import com.uc.training.smadmin.ord.re.OrderGoodsDetailRE;
import com.uc.training.smadmin.ord.re.OrderRE;
import com.uc.training.smadmin.ord.service.OrderService;
import com.uc.training.smadmin.ord.vo.OrdOrderVO;
import com.ycc.base.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
//@Controller
//@RequestMapping(value = "admin/order/")
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
	 * @author hhj
	 */
	@ResponseBody
	@AccessLogin
	@RequestMapping(value = "getOrderPage.do_", method = RequestMethod.POST)
	public Result getOrderPage(OrdOrderVO orderVo) {
		List<OrderRE> list;
		int size = 5;
		Map<String, Object> map = new HashMap<String, Object>(size);
		list = orderService.getOrderPage(orderVo);
		Integer totalSize = orderService.getOrderTotal(orderVo);
		map.put("orderList", list);
		map.put("totalSize", totalSize);
		return Result.getSuccessResult(map);
	}

	/**
	 * 批量删除订单（更改删除/进度状态）
	 *
	 * @param delId（orderVo.getOrderListStr()）
	 * @return
	 * @author hhj
	 */
	@ResponseBody
	@AccessLogin
	@RequestMapping(value = "admDelOrder.do_", method = RequestMethod.POST)
	public Result delOrderPage(String[] delId) {
		List<Long> list = new ArrayList<Long>();
		if (delId != null) {
			for (String s : delId) {
				list.add(Long.parseLong(s));
			}
		} else {
			return Result.getBusinessException("删除失败", null);
		}
		int num = orderService.logicDelOrder(list);

		if (num > 0) {
			return Result.getSuccessResult(null);
		}
		return Result.getBusinessException("删除失败", null);
	}

	/**
	 * 获取订单商品详情
	 *
	 * @param id(订单id)
	 * @return
	 * @author hhj
	 */
	@ResponseBody
	@AccessLogin
	@RequestMapping(value = "getOrderGoods.do_", method = RequestMethod.POST)
	public Result getOrderGoods(Integer id) {
		List<OrderGoodsDetailRE> list;
		try {
			list = orderService.getOrderGdsById(id);
		} catch (Exception e) {
			return Result.getBusinessException("获取异常", null);
		}
		return Result.getSuccessResult(list);
	}

	/**
	 * 根据订单号更新状态
	 *
	 * @param ordOrderVO
	 * @return
	 * @author hhj
	 */
	@ResponseBody
	@AccessLogin
	@RequestMapping(value = "upOrderStatus.do_", method = RequestMethod.POST)
	public Result upOrderStatus(OrdOrderVO ordOrderVO) {
		ordOrderVO.setModifyEmp(getUid());
		//取消订单回退库存
		if (orderService.updateOrder(ordOrderVO) > 0) {
			return Result.getSuccessResult(null);
		}
		return Result.getBusinessException("更新失败", null);
	}
}
