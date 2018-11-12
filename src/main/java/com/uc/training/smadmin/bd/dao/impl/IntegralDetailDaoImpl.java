package com.uc.training.smadmin.bd.dao.impl;

import com.uc.training.smadmin.bd.dao.IntegralDetailDao;
import com.uc.training.smadmin.bd.model.IntegralDetaill;
import com.zuche.framework.dao.CarIsIbatisDaoImpl;
import org.springframework.stereotype.Repository;

/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：shixian.zhang@ucarinc.com
 * @version：v1.0
 * @date: 2018/10/26
 * 说明：积分详情实现
 */
@Repository
public class IntegralDetailDaoImpl extends CarIsIbatisDaoImpl implements IntegralDetailDao {
    @Override
    public void addIntegralDetail(IntegralDetaill integralDetaill) {
        this.insert("com.uc.training.smadmin.bd.dao.IntegralDetailDao.addIntegralDetail", integralDetaill);
    }
}
