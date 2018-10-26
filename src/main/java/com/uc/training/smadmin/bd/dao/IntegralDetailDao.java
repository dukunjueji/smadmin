package com.uc.training.smadmin.bd.dao;

import com.uc.training.smadmin.bd.model.IntegralDetaill;
import com.uc.training.smadmin.bd.vo.IntegralVO;

/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：shixian.zhang@ucarinc.com
 * @version：v1.0
 * @date: 2018/10/26
 * 说明：积分详情接口
 */
public interface IntegralDetailDao {

    /**
    *说明：添加积分详情
    *@param integralDetaill
    *@return：void
    *@throws：
    */
    public void addIntegralDetail(IntegralDetaill integralDetaill);
}
