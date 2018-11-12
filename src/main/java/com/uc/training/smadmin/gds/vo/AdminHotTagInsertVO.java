package com.uc.training.smadmin.gds.vo;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2018/11/6
 */
public class AdminHotTagInsertVO implements Serializable {

    private static final long serialVersionUID = 607682340909040884L;
    /**
     * 标签名称
     */
    @NotBlank(message = "标签名称不能为空！")
    private String tag;
    /**
     * 排序号
     */
    @NotNull(message = "排序号不能为空")
    private Integer sortNum;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getSortNum() {
        return sortNum;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }

    @Override
    public String toString() {
        return "AdminHotTagInsertVO{" +
                "tag='" + tag + '\'' +
                ", sortNum=" + sortNum +
                '}';
    }
}
