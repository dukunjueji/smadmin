package com.uc.training.common.bean;


import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;

/**
 * 分页查询基础类
 *
 * @author 吴佰川（baichuan.wu@ucarinc.com）创建
 * @version 1.0
 * @date 2018/10/25 18:04
 */
public class PageQuery implements Serializable {

    /**
     * 初始位置
     */
    public transient Integer offset;

    /**
     * 当前页数
     */
    public Integer pageIndex = 1;

    /**
     * 页长
     */
    public Integer pageSize = 12;

    public Integer getOffset() {
        if (offset != null) {
            return this.offset;
        }
        if (pageIndex == null) {
            return null;
        }
        return (pageIndex - 1) * pageSize;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
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

    @Override
    public String toString() {
        return "PageQuery{" +
                "offset=" + offset +
                ", pageIndex=" + pageIndex +
                ", pageSize=" + pageSize +
                '}';
    }
}
