package com.uc.training.smadmin.gds.vo;

import com.ycc.base.common.BaseDomain;

/**
 * 分页查询类
 *
 * @author zhongling(ling.zhong @ ucarinc.com)
 * @since 2018年11月02日 10:22
 */
public class PageVO<T> extends BaseDomain{
    /**
     * 查询特定条件
     */
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
