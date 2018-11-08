package com.uc.training.smadmin.bd.dao.impl;

import com.uc.training.smadmin.bd.dao.GrowthDetailDao;
import com.uc.training.smadmin.bd.model.GrowthDetail;
import com.zuche.framework.dao.CarIsIbatisDaoImpl;
import org.springframework.stereotype.Repository;

/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：shixian.zhang@ucarinc.com
 * @version：v1.0
 * @date: 2018/10/26
 * 说明：成长值详情实现
 */
@Repository
public class GrowthDetailDaoImpl extends CarIsIbatisDaoImpl implements GrowthDetailDao {
    @Override
    public void saveGrowthDetail(GrowthDetail growthDetail) {
        this.update("com.uc.training.smadmin.bd.dao.GrowthDetailDao.saveGrowthDetail", growthDetail);
    }
}
