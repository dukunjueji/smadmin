package com.uc.training.remoteClient;

import com.uc.training.utils.RemoteUtil;
import com.uc.training.ord.dto.OrderDTO;
import com.uc.training.ord.re.OrderRE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/11/22
 */
@Service
public class OrderClient {

    private static final Logger logger = LoggerFactory.getLogger(OrderClient.class.getName());

    /**根据id获取订单信息*/
    private static final String GET_ORDER_INFO_BY_ID = "smorder.api.getOrderInfoById";

    /**
     * 根据id获取订单信息
     * @return
     */
    public OrderRE getOrderInfoById(OrderDTO orderDTO) {
        return (OrderRE) RemoteUtil.exec(GET_ORDER_INFO_BY_ID, orderDTO).getRe();
    }

}
