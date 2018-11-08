package com.uc.training.common.bean;


import java.io.Serializable;

/**
 * 分页查询基础类
 *
 * @author 吴佰川（baichuan.wu@ucarinc.com）创建
 * @version 1.0
 * @date 2018/10/25 18:04
 */
public class PageQuery implements Serializable {
    private static final long serialVersionUID = -7566819271440512306L;
    /**
     * 默认首页
     */
    private static final int PAGE_INDEX = 1;
    /**
     * 默认页数
     */
    private static final int PAGE_SIZE = 15;


    /**
     * 初始位置
     */
    public transient Integer offset;

    /**
     * 当前页数
     */
    public Integer pageIndex = PAGE_INDEX;

    /**
     * 页长
     */
    public Integer pageSize = PAGE_SIZE;

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
