package com.uc.training.base.bd.service.impl;

import com.uc.training.smadmin.bd.dao.LoginLogDao;
import com.uc.training.smadmin.bd.model.LoginLog;
import com.uc.training.smadmin.bd.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：shixian.zhang@ucarinc.com
 * @version：v1.0
 * @date: 2018/11/1
 * 说明：
 */
@Service
public class LoginLogServiceImpl implements LoginLogService {

    @Autowired
    private LoginLogDao loginLogDao;

    @Override
    public void insertLog(LoginLog loginLog) {
        this.loginLogDao.insertLog(loginLog);
    }

    @Override
    public Integer queryLoginCount(LoginLog loginLog) {
        return this.loginLogDao.queryLoginCount(loginLog);
    }
}
