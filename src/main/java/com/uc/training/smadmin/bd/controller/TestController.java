package com.uc.training.smadmin.bd.controller;

import com.uc.training.common.base.controller.BaseController;
import com.uc.training.smadmin.bd.service.GrowthDetailService;
import com.uc.training.smadmin.bd.service.IntegralDetailService;
import com.uc.training.smadmin.bd.vo.GrowthVO;
import com.uc.training.smadmin.bd.vo.IntegralVO;
import com.ycc.base.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;


/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：shixian.zhang@ucarinc.com
 * @version：v1.0
 * @date: 2018/10/26
 * 说明：
 */
@Controller
@RequestMapping("api/test/test")
public class TestController extends BaseController {
    @Autowired
    private IntegralDetailService integralDetailService;

    @Autowired
    private GrowthDetailService growthDetailService;

    @ResponseBody
    @RequestMapping("/test.do_")
    public Result test(){
        GrowthVO growthVO = new GrowthVO();
        growthVO.setMemberId(1L);
        growthVO.setGrowthType(2);
        growthDetailService.saveGrowthDetail(growthVO);
        return Result.getSuccessResult("ni");
    }
}
