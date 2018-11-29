package com.uc.training.remote.client;

import com.uc.training.base.re.MemberRE;
import com.uc.training.remote.utils.RemoteUtil;
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
public class BaseClient {

    private static final Logger logger = LoggerFactory.getLogger(BaseClient.class.getName());

    /** 根据id查询会员信息*/
    private static final String GET_MEMBER_INFO_BY_ID = "smgds.api.getGoodsInfoById";

    /**
     * 根据商品id查询商品信息
     * @return
     */
    public MemberRE getCarGoodsById(Long id) {
        MemberRE memberRE = new MemberRE();
        try {
            memberRE = (MemberRE) RemoteUtil.exec(GET_MEMBER_INFO_BY_ID, id);
            return memberRE;
        }catch (Exception e){
            logger.error("类型转换异常");
            logger.error(e.getMessage());
        }
        return memberRE;
    }
}
