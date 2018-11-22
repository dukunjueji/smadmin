package com.uc.training.remote.client;

import com.uc.training.gds.dto.GoodsDTO;
import com.uc.training.smadmin.gds.re.GoodsRE;
import com.uc.training.utils.RemoteUtil;
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
public class GdsClient {

    private static final Logger logger = LoggerFactory.getLogger(GdsClient.class.getName());

    /** 根据id查询商品信息*/
    private static final String GET_GOODS_INFO_BY_ID = "smgds.api.getGoodsInfoById";

    /**
     * 根据商品id查询商品信息
     * @return
     */
    public GoodsRE getCarGoodsById(GoodsDTO goodsDTO) {
        GoodsRE goodsRE = new GoodsRE();
        try {
            goodsRE = (GoodsRE) RemoteUtil.exec(GET_GOODS_INFO_BY_ID, goodsDTO);
            return goodsRE;
        }catch (Exception e){
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return goodsRE;
    }

}
