package com.uc.training.common.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：shixian.zhang@ucarinc.com
 * @version：v1.0
 * @date: 2018/10/18
 * 说明：加密工具类
 */
public class EncryptUtil {
    /**
     * MD5加密
     *
     * @param data
     * @return
     */
    public static String md5(String data) {
        return DigestUtils.md5Hex(data);
    }

    public static void main(String[] args) {
        System.out.println(md5("123456"));
    }
}
