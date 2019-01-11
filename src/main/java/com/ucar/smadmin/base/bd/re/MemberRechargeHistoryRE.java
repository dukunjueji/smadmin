package com.ucar.smadmin.base.bd.re;

import java.io.Serializable;
import java.util.List;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/11/15
 */
public class MemberRechargeHistoryRE implements Serializable {

    private static final long serialVersionUID = 4704450115454910585L;
    /**
     * 充值记录详情列表
     */
    private List<MemberRechargeHistoryListRE> memberRechargeHistoryList;

    private Integer totalNum;

    public List<MemberRechargeHistoryListRE> getMemberRechargeHistoryList() {
        return memberRechargeHistoryList;
    }

    public void setMemberRechargeHistoryList(List<MemberRechargeHistoryListRE> memberRechargeHistoryList) {
        this.memberRechargeHistoryList = memberRechargeHistoryList;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }
}
