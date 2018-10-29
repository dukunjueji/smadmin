package com.uc.training.smadmin.bd.vo;

import com.uc.training.common.vo.PageVO;

/**
 * @Author: 余旭东
 * @Date: 2018/10/29 15:53
 * @Description:
 */
public class MemberListVO extends PageVO {
    private String telephone;
    private String memberName;

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
}