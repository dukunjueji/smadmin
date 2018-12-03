package com.uc.training.base.bd.service;

import com.uc.training.base.bd.vo.GrowthVO;

/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：shixian.zhang@ucarinc.com
 * @version：v1.0
 * @date: 2018/10/26
 * 说明：成长值详情逻辑
 */
public interface GrowthDetailService {
    /**
    *说明：保存成长值详情和更新会员成长值
    *@param growthVO
    *@return：java.lang.Long
    *@throws：
    */
    Long saveGrowthDetail(GrowthVO growthVO);
}
