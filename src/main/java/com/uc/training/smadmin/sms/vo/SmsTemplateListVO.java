package com.uc.training.smadmin.sms.vo;

import com.uc.training.common.bean.PageQuery;

/**
 * @Author: 余旭东
 * @Date: 2018/10/22 9:52
 * @Description:
 */
public class SmsTemplateListVO extends PageQuery {
    private static final long serialVersionUID = -384549456196291123L;
    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

}
