package com.uc.training.base.bd.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/11/29
 */
public class MemberVO implements Serializable {

    private static final long serialVersionUID = 6557847513925405009L;

    private Long id;

    /**
     * 用户手机号
     */
    private String telephone;

    /**
     * 密码
     */
    private String password;
    /**
     * 余额
     */
    private BigDecimal balance;
    /**
     * 头像
     */
    private String imageUrl;

    /**
     * 用户名
     */
    private String memberName;
    /**
     * 性别
     */
    private Integer sex;
    /**
     * 邮箱
     */
    private String email;

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

    /**
     * 昵称
     */
    private Long modifyEmp;

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
                "id=" + id +
                ", telephone='" + telephone + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                ", imageUrl='" + imageUrl + '\'' +
                ", memberName='" + memberName + '\'' +
                ", sex=" + sex +
                ", email='" + email + '\'' +
                ", growth=" + growth +
                ", integral=" + integral +
                ", nickname='" + nickname + '\'' +
                ", modifyEmp=" + modifyEmp +
                '}';
    }

    public Long getModifyEmp() {
        return modifyEmp;
    }

    public void setModifyEmp(Long modifyEmp) {
        this.modifyEmp = modifyEmp;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
