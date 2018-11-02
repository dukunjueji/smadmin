package com.uc.training.smadmin.bd.dao.impl;

import com.uc.training.smadmin.bd.dao.LoginLogDao;
import com.uc.training.smadmin.bd.model.LoginLog;
import com.zuche.framework.dao.CarIsIbatisDaoImpl;
import org.springframework.stereotype.Repository;

/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：shixian.zhang@ucarinc.com
 * @version：v1.0
 * @date: 2018/11/1
 * 说明：
 */
@Repository
public class LoginLogDaoImpl extends CarIsIbatisDaoImpl implements LoginLogDao {
    @Override
    public void insertLog(LoginLog loginLog) {
        this.insert("com.uc.training.smadmin.bd.dao.LoginLogDao.insertLog", loginLog);
    }

    @Override
    public Integer queryLoginCount(LoginLog loginLog) {
        return (Integer) this.queryForObject("com.uc.training.smadmin.bd.dao.LoginLogDao.queryLoginCount", loginLog);
    }
}
