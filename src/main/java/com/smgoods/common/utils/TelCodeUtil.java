package com.smgoods.common.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：shixian.zhang@ucarinc.com
 * @version：v1.0
 * @date: 2018/10/16
 * 说明：随机生成六位数字作为手机验证码
 */
public final class TelCodeUtil {

    private TelCodeUtil() {
    }

    private static final Integer START = 3;

    private static final Integer END = 9;
    /**
     * 随机生成六位数短信验证码
     * @return
     */
    public static String createCode(){
        String[] array = new String[] { "1","2", "3", "4", "5", "6", "7",
                "8", "9", "0" };
        //将数组转成List
        List list = Arrays.asList(array);
        Collections.shuffle(list);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
        }
        String telCode = sb.toString().substring(START, END);
        return telCode;
    }

    /**
     *校验手机号码格式
     * @param cellphone 手机号码
     * @return
     */
    public static boolean validateTel(String cellphone){
        if (cellphone == null){
            return false;
        }
        String regExp = "^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])[0-9]{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(cellphone);
        return m.matches();
    }

}
