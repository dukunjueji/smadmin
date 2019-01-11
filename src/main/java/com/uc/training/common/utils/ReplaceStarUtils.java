package com.uc.training.common.utils;
import org.apache.commons.lang.StringUtils;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/12/12
 */
public final class ReplaceStarUtils {
    /**
     * 实际替换动作
     *
     * @param userName userName
     * @return
     */
    public static String replaceAction(String userName) {
        String userNameAfterReplaced = "";
        int nameLength = userName.length();
        if(nameLength<3 && nameLength>0){
            if(nameLength==1){
                userNameAfterReplaced = "*";
            }else{
                userNameAfterReplaced = userName.replaceAll(userName, "^.{1,2}");
            }
        }else{
            Integer num1,num2,num3;
            num2=(new Double(Math.ceil(new Double(nameLength)/3))).intValue();
            num1=(new Double(Math.floor(new Double(nameLength)/3))).intValue();
            num3=nameLength-num1-num2;
            String star= StringUtils.repeat("*",num2);
            userNameAfterReplaced = userName.replaceAll("(.{"+num1+"})(.{"+num2+"})(.{"+num3+"})","$1"+star+"$3");
        }
        return userNameAfterReplaced;
    }
}
