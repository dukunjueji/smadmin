package com.uc.training.base.bd.re;

import java.io.Serializable;
import java.util.List;

/**
 * 版权说明：Copyright (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author：shixian.zhang@ucarinc.com
 * @version：v1.0
 * @date: 2018/11/1
 * 说明：
 */
public class AllMessageRE implements Serializable {
    private static final long serialVersionUID = -8351051564210312258L;

    /**
     * 所有消息
     */
    private List<MessageRE> messageREList;

    /**
     * 消息的总数
     */
    private Integer totalNum;

    public List<MessageRE> getMessageREList() {
        return messageREList;
    }

    public void setMessageREList(List<MessageRE> messageREList) {
        this.messageREList = messageREList;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }
}
