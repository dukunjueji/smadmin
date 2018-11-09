package com.uc.training.smadmin.sys.vo;

import com.uc.training.common.bean.PageQuery;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * @Author: 余旭东
 * @Date: 2018/10/31 17:22
 * @Description:
 */
public class UserListVO extends PageQuery {
    /**
     * 用户ID
     */
    private Long id;

    /**
     * 用户名
     */
    @Length(max = 32, message = "用户名长度不能超过32位")
    private String name;

    /**
     * 开始时间
     */
    private Date startDate;

    /**
     * 结束时间
     */
    private Date endDate;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
