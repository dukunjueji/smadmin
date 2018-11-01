package com.uc.training.smadmin.testMq;

import com.uc.training.smadmin.ord.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * description: TODO
 *
 * @author 黄宏俊（hongjun.huang01@ucarinc.com）
 * @version 1.0
 * @date 2018/11/1  18:13
 */
@Controller
public class Test {
  @Autowired
  OrderService orderService;

  List<Long> list;

  Integer um = 0;

  public Test(List list) {
    this.list = list;
    this.um = orderService.logicDelOrder(list);
  }

  public Integer getUm() {
    return this.um;
  }
}
