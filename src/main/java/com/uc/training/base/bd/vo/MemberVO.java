package com.uc.training.base.bd.vo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/11/29
 */
public class MemberVO {

    /**
     * 用户手机号
     */
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])[0-9]{8}$", message = "手机号格式不正确")
    private String telephone;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    @Length(min = 6, max = 10, message = "密码长度须在6-10位之间")
    private String password;

    /**
     * 用户名
     */
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
        return "MemberVO{" +
                "telephone='" + telephone + '\'' +
                ", password='" + password + '\'' +
                ", telCode='" + telCode + '\'' +
                ", memberName='" + memberName + '\'' +
                ", sex=" + sex +
                ", growth=" + growth +
                ", integral=" + integral +
                ", nickname='" + nickname + '\'' +
                '}';
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelCode() {
        return telCode;
    }

    public void setTelCode(String telCode) {
        this.telCode = telCode;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
