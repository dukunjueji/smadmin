package com.uc.training.smadmin.gds.re;

/**
 * 分页结果返回类
 *
 * @author zhongling(ling.zhong @ ucarinc.com)
 * @since 2018年11月02日 10:47
 */
public class PageRE<T> {
    /**
     * 所有消息
     */
    private T data;
    /**
     * 消息的总数
     */
    private Integer totalNum;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }
}
