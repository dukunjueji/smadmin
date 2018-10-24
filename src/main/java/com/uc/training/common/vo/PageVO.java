package com.uc.training.common.vo;

import java.util.List;

/**
 * 分页
 *
 * @version 1.0 2018/10/17 12:27 by 吴佰川（baichuan.wu@ucarinc.com）创建
 * @copyright www.ucarinc.com All Rights Reserved.
 */
public class PageVO<T> {
    /**
     * 默认当前页码
     */
    private final static int DEFAULT_PAGE_NO = 1;

    /**
     * 默认查询记录数
     */
    private final static int DEFAULT_PAGE_SIZE = 16;

    /**
     * 页码
     */
    private Integer pageIndex = DEFAULT_PAGE_NO;

    /**
     * 页码
     */
    private Integer pageSize = DEFAULT_PAGE_SIZE;

    /**
     * 结果列表
     */
    private List<T> dataList;

    /**
     * 总条数
     */
    private Long total;

    public PageVO() {
    }

    public PageVO(Long total, List<T> dataList) {
        this.total = total;
        this.dataList = dataList;
    }

    public PageVO(Long total, Integer pageIndex, Integer pageSize, List<T> dataList) {
        this.total = total;
        this.pageIndex = pageIndex;
        this.dataList = dataList;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }
}
