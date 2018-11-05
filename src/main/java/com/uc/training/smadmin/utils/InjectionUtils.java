package com.uc.training.smadmin.utils;

import com.zuche.framework.common.SpringApplicationContext;

import java.util.Map;

/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：shixian.zhang@ucarinc.com
 * @version：v1.0
 * @date: 2018/11/3
 * 说明：注入工具类
 */
public class InjectionUtils {
    public static <T>T getInjectionInstance(Class<T> type){
        Map<String, T> tMap = SpringApplicationContext.getBeansByType(type);
        return tMap.values().iterator().next();
    }
}
