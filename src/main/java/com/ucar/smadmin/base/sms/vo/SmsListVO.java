package com.ucar.smadmin.base.sms.vo;

import com.ucar.smapi.common.bean.PageQuery;

import java.util.Date;

/**
 * @Author: 余旭东
 * @Date: 2018/10/22 11:25
 * @Description:
 */
public class SmsListVO extends PageQuery {
    private static final long serialVersionUID = -384547466196734123L;

    /**
     * 收短信的手机号
     */
    private String telephone;

    /**
     * 短信类型
     */
    private Integer type;

    /**
     * 发送状态 0：失败 1：成功
     */
    private Integer status;

    /**
     * 查询的起始日期
     */
    private Date startDate;

    /**
     * 查询的终止日期
     */
    private Date endDate;

    /**
     * 字符串日期
     */
    private String time;

    public String getTime() {
        return time;
    }


    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getStartDate() {
        return startDate == null ? null : (Date) startDate.clone();
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate == null ? null : (Date) startDate.clone();
    }

    public Date getEndDate() {
        return endDate == null ? null : (Date) endDate.clone();
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate == null ? null : (Date) endDate.clone();
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "SmsListVO{" +
                "telephone='" + telephone + '\'' +
                ", type=" + type +
                ", status=" + status +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
