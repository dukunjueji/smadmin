package com.uc.training.common.bean;


import java.io.Serializable;

/**
 * 分页
 *
 * @copyright www.ucarinc.com All Rights Reserved.
 * @version 1.0 2018/10/17 11:37 by 吴佰川（baichuan.wu@ucarinc.com）创建
 */
public class PageQuery implements Serializable {

    /**
     * 初始位置
     */
    public transient Integer offset;
    /**
     * 页长
     */
    public Integer pageSize = 20;
    /**
     * 当前页数
     */
    public Integer pageIndex;

    public Integer getOffset() {
        if (offset != null) {
            return this.offset;
        }
        if (pageIndex == null) {
            return null;
        }
        return (pageIndex - 1) * pageSize;
    }


    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }
}
