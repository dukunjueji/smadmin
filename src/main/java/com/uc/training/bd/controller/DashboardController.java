package com.uc.training.bd.controller;

import com.uc.training.smadmin.ord.service.OrderService;
import com.uc.training.smadmin.sys.re.OrderSaleRE;
import com.ycc.base.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Dashboard
 *
 * @author 吴佰川（baichuan.wu@ucarinc.com）创建
 * @version 1.0
 * @date 2018/11/12 11:28
 */
@Controller
@RequestMapping(value = "admin/sys/dashboard/")
public class DashboardController {
    @Autowired
    private OrderService orderService;

    /**
     * 统计当年每个月销售额
     *
     * @version 1.0 2018/11/12 11:30 by 吴佰川（baichuan.wu@ucarinc.com）创建
     * @param
     * @return com.ycc.base.common.Result<java.util.List<java.math.BigDecimal>>
     */
    @ResponseBody
    @RequestMapping(value = "/getOrderSaleData.do_", method = RequestMethod.GET)
    public Result<OrderSaleRE> getOrderSaleData(){
        return Result.getSuccessResult(orderService.getOrderSaleData());
    }
}
