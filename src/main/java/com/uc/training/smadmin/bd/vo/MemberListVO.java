package com.uc.training.smadmin.bd.vo;

import com.uc.training.common.vo.PageVO;
import org.hibernate.validator.constraints.Length;

/**
 * @Author: 余旭东
 * @Date: 2018/10/29 15:53
 * @Description:
 */
public class MemberListVO extends PageVO {
    /**
     * 用户手机号
     */
    private String telephone;
    /**
     * 用户名
     */
    @Length(max = 32, message = "用户名长度不能超过32位")
    private String memberName;
    /**
     * 性别
     */
    private Integer sex;

    /**
     * 成长值
     */
    private Long growth;

    /**
     * 积分值
     */
    private Long integral;

    /**
     * 昵称
     */
    private String nickname;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Long getGrowth() {
        return growth;
    }

    public void setGrowth(Long growth) {
        this.growth = growth;
    }

    public Long getIntegral() {
        return integral;
    }

    public void setIntegral(Long integral) {
        this.integral = integral;
    }

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

    @Override
    public String toString() {
        super.toString();
        return "电话：" + telephone + ", 用户名：" + memberName;
    }
}