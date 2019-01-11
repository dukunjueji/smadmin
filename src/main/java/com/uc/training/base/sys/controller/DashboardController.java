package com.uc.training.base.sys.controller;

import com.uc.training.common.base.controller.BaseController;
import com.uc.training.common.vo.Result;
import com.uc.training.ord.re.OrderSaleRE;
import com.uc.training.ord.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: 余旭东
 * @Date: 2018/11/30 13:35
 * @Description:
 */
@Controller
@RequestMapping(value = "admin/sys/dashboard/")
public class DashboardController extends BaseController {
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
