package com.uc.training.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 余旭东
 * @Date: 2018/10/25 14:07
 * @Description: 短信模板替换字符集
 */
public enum SmsTemplateReplaceEnum {

    VERIFICATIONCODE("验证码", "${code}"),
    MEMBERNAME("用户名", "${memberName}"),
    ORDERNUM("订单编号", "${orderNumber}");

    /**
     * 描述
     */
    private String descript;

    /**
     * 被替换的字符
     */
    private String replaceString;

    SmsTemplateReplaceEnum(String descript, String replaceString){
        this.descript = descript;
        this.replaceString = replaceString;
    }

    public static Map<String, String> getMap(){
        Map<String, String> map = new HashMap<>();
        for (SmsTemplateReplaceEnum s : SmsTemplateReplaceEnum.values()){
            map.put(s.replaceString, s.descript);
        }
        return map;
    }
}
