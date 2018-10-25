package com.uc.training.smadmin.sms.vo;

import com.uc.training.common.bean.PageQuery;

/**
 * @Author: 余旭东
 * @Date: 2018/10/22 9:52
 * @Description:
 */
public class SmsTemplateListVO extends PageQuery {
    private static final long serialVersionUID = -384549456196291123L;
    /**
     * 短信类型
     */
    private Integer type;

    /**
     * 短信编码
     */
    private String code;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
