package com.uc.training.base.sms.vo;

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
     * 短信类型：1注册，2，忘记密码，3修改密码，4充值，5订单信息，6会员等级
     */
    private Integer type;

    /**
     * 发送状态：0失败，1成功
     */
    private Integer status;

    /**
     * 短信内容
     */
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

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



    @Override
    public String toString() {
        return "SmsVO{" +
                "id=" + id +
                ", telephone='" + telephone + '\'' +
                ", type=" + type +
                ", status=" + status +
                ", content='" + content + '\'' +
                '}';
    }
}
