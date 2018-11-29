package com.uc.training.base.bd.service;

import com.uc.training.smadmin.bd.model.LoginLog;

/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：shixian.zhang@ucarinc.com
 * @version：v1.0
 * @date: 2018/11/1
 * 说明：
 */
public interface LoginLogService {
    /**
    *说明：插入登陆日志
    *@param loginLog
    *@return：void
    *@throws：
    */
    void insertLog(LoginLog loginLog);

    /**
    *说明：会员当天登陆的次数
    *@param loginLog
    *@return：java.lang.Integer
    *@throws：
    */
    Integer queryLoginCount(LoginLog loginLog);
}
