package com.uc.training.utils;

import com.uc.training.common.vo.RemoteResult;
import com.zuche.framework.remote.RemoteClientFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/11/22
 */
public final class RemoteUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(RemoteUtil.class);

    /**
     * 执行远程调用
     * @param serviceName
     * @param object
     * @return
     */
    public static Object exec(String serviceName, Object object) {
        Object result = null;
        try {
            result = RemoteClientFactory.getInstance().executeToObject(serviceName, object);
        } catch (Exception e){
            LOGGER.error("调用远程服务异常！",e);
        }
        if (result == null) {
            LOGGER.error("远程服务返回结果为null");
            return null;
        }
        if (result instanceof RemoteResult) {
            RemoteResult remoteResult = (RemoteResult)result;
            if (remoteResult.getStatus() == 0 && remoteResult.getRe() != null) {
                return remoteResult.getRe();
            } else {
                LOGGER.error(remoteResult.getMsg());
            }
        }
        return null;
    }

}
