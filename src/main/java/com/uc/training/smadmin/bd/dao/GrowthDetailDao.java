package com.uc.training.smadmin.bd.dao;

import com.uc.training.smadmin.bd.model.GrowthDetail;

/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：shixian.zhang@ucarinc.com
 * @version：v1.0
 * @date: 2018/10/26
 * 说明：成长值详情接口
 */
public interface GrowthDetailDao {

    /**
    *说明：添加积分详情
    *@param growthDetail
    *@return：void
    *@throws：
    */
    void saveGrowthDetail(GrowthDetail growthDetail);
}
