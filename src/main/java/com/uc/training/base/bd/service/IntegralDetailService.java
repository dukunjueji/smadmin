package com.uc.training.base.bd.service;

import com.uc.training.base.bd.vo.IntegralVO;

/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：shixian.zhang@ucarinc.com
 * @version：v1.0
 * @date: 2018/10/26
 * 说明：积分详情逻辑接口
 */
public interface IntegralDetailService {

    /**
     *说明：保存积分详情和修改会员积分
     *@param integralVO
     *@return：java.lang.Long
     *@throws：
     */
    Long saveIntegralDetail(IntegralVO integralVO);

}
