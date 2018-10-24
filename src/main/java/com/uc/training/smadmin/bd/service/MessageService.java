package com.uc.training.smadmin.bd.service;

/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：shixian.zhang@ucarinc.com
 * @version：v1.0
 * @date: 2018/10/24
 * 说明：
 */
public interface MessageService {
    /**
     * 查找指定会员消息总记录数
     */
    public Integer queryMessageCount(Long memberId);
}
