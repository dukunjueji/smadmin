package com.ucar.smadmin.base.sms.vo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * @Author: 余旭东
 * @Date: 2018/11/2 18:05
 * @Description:
 */
public class SmsTemplateVO {
    private Long id;
    @NotNull
    private Integer type;
    @NotBlank
    @Length(max = 255, message = "标题长度不能超过255位")
    private String title;
    @NotBlank
    @Length(max = 255, message = "内容长度不能超过255位")
    private String content;
    @Length(max = 10, message = "编号长度不能超过10位")
    private String code;
    private Long createEmp;
    private Long modifyEmp;

    public Long getCreateEmp() {
        return createEmp;
    }

    public void setCreateEmp(Long createEmp) {
        this.createEmp = createEmp;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getModifyEmp() {
        return modifyEmp;
    }

    public void setModifyEmp(Long modifyEmp) {
        this.modifyEmp = modifyEmp;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "SmsTemplateVO{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
