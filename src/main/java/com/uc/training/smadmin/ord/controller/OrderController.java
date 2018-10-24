package com.uc.training.smadmin.ord.controller;

import com.ycc.base.common.Result;
import com.uc.training.common.base.controller.BaseController;
import com.uc.training.smadmin.gds.service.GoodsService;
import com.uc.training.smadmin.ord.model.OrdOrder;
import com.uc.training.smadmin.ord.service.OrderService;
import com.uc.training.smadmin.ord.vo.OrdCartGoodsVo;
import com.uc.training.smadmin.ord.vo.OrdOrderGoodsVo;
import com.uc.training.smadmin.ord.vo.OrdOrderVo;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单控制类
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

    @ResponseBody
    @RequestMapping(value = " ",method = RequestMethod.GET)
    public Result getCartgds() {
        Result result = new Result();
        List<OrdCartGoodsVo> list;
        //memberId = UID();
        list = orderService.getCarGoodsById( 1L);
        OrdCartGoodsVo cartGoodsVo = new OrdCartGoodsVo();
        result.setRe(list);
        return result;
    }
    @ResponseBody
    @RequestMapping(value="updataCartgoods.do_",method = RequestMethod.POST)
    public Result updataCartgds(HttpServletRequest request,OrdCartGoodsVo ordCartGoodsVo) {
        Result result = new Result();
        ordCartGoodsVo.setMemberId(1L);
        orderService.updataCarGoodsNum(ordCartGoodsVo);
        return result;
    }
    @ResponseBody
    @RequestMapping(value = "getOrderList.do_",method = RequestMethod.GET)
    public Result<List<OrdOrderGoodsVo>> getOrderGds(String goodsList ) {
        List<OrdOrderGoodsVo> orderGodsList = (List<OrdOrderGoodsVo>) JSONArray.toList(JSONArray.fromObject(goodsList), new OrdOrderGoodsVo(), new JsonConfig());
        Result<List<OrdOrderGoodsVo>> orderList = Result.getSuccessResult(orderService.getOrderGoodsById(orderGodsList));
        return orderList;
    }

    @ResponseBody
    @RequestMapping("addCartgoods.do_")
    public Result addCartgds(HttpServletRequest request,OrdCartGoodsVo ordCartGoodsVo) {
        Result result = new Result();
        List<OrdCartGoodsVo> list;
        //memberId = UID();
        list = orderService.getCarGoodsById( 1L);
        for (OrdCartGoodsVo cartGdsList: list) {
            if(cartGdsList.getPropertyId().equals(ordCartGoodsVo.getPropertyId())){
                result.setMsg("该商品在购物车已经存在！！");
                return result;
            }
        }
        orderService.addCarGoods(ordCartGoodsVo);
        return result;
    }
    @ResponseBody
    @RequestMapping(value = "deleteCartgoods.do_",method = RequestMethod.POST)
    public Result deleteCartgds(HttpServletRequest request,OrdCartGoodsVo ordCartGoodsVo) {
        Result result = new Result();
        orderService.deleteCarGoods(ordCartGoodsVo);
        return result;
    }



    @RequestMapping(value = "updateOrderInfo.do_",method = RequestMethod.POST)
    public Result updateOrderInfo(String orderInfoList){
        List<OrdOrderGoodsVo> orderInfoListNow = (List<OrdOrderGoodsVo>) JSONArray.toList(JSONArray.fromObject(orderInfoList), new OrdOrderGoodsVo(), new JsonConfig());
        orderService.updateOrderInfo(orderInfoListNow);
        Result result = new Result();
        return result;
    }
}
