package com.uc.training.utils;

import com.uc.training.common.vo.RemoteResult;
import com.uc.training.ord.re.CartGoodsRE;
import com.zuche.framework.remote.RemoteClientFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

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
     * 验证remoteResult结果
     * @param object
     * @return
    public static <T> T validateResult(Object object, Class<T> t){
        try {
            RemoteResult<T> result = (RemoteResult<T>)object;

            if(result != null && result.getMsg().equals("SUCCESS") && result.getRe() != null) {
                return result.getRe();
            }
        }catch (Exception e){
            LOGGER.error(e.getMessage());
        }
        return null;
    }
*/
    /**
     * 执行远程调用
     * @param serviceName
     * @param object
     * @return
     */
    public static RemoteResult exec(String serviceName, Object object) {
        try {
            Object result = RemoteClientFactory.getInstance().executeToObject(serviceName, object);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
