package com.uc.training.smadmin.sms.vo;

import com.uc.training.common.bean.PageQuery;
import org.apache.commons.lang.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    private String startTime;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
        DateFormat ds = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            this.startDate = ds.parse(startTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
        DateFormat ds = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            this.endDate = ds.parse(endTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private String endTime;

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
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
