package com.uc.training.smadmin.sms.vo;

import com.uc.training.common.enums.SmsStatusEnum;
import com.uc.training.common.enums.SmsTemplateTypeEnum;

import java.util.Date;

/**
 * @Author: 余旭东
 * @Date: 2018/10/25 9:42
 * @Description:
 */
public class SmsVO {
    /**
     * 自增主键ID
     */
    private Long id;

    /**
     * 目标用户手机号
     */
    private String telephone;

    /**
     * 短信类型：1注册，2，登录，3充值，4订单信息，5会员等级
     */
    private Integer type;

    /**
     * 发送状态：0失败，1成功
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

    private String typeDes;

    private String statusDes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTypeDes() {
        return SmsTemplateTypeEnum.getMap().get(type);
    }


    public String getStatusDes() {
        return SmsStatusEnum.getMap().get(status);
    }

    @Override
    public String toString() {
        return "SmsVO{" +
                "id=" + id +
                ", telephone='" + telephone + '\'' +
                ", type=" + type +
                ", status=" + status +
                ", createTime=" + createTime +
                ", typeDes='" + typeDes + '\'' +
                ", statusDes='" + statusDes + '\'' +
                '}';
    }
}
