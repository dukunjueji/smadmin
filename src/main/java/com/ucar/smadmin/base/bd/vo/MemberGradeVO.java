package com.ucar.smadmin.base.bd.vo;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * @author xudong.yu@ucarinc.com
 * @date 2018-10-17 星期三 15:41
 * @description:
 */
public class MemberGradeVO implements Serializable {

    private static final long serialVersionUID = 2277243200584101709L;

    /**
     * 主键id
     **/
    private Long id;

    /**
     * 等级名称
     **/
    private String name;

    /**
     * 等级下限
     **/
    private Long minGrowth;

    /**
     * 等级上限
     **/
    private Long maxGrowth;

    private Long createEmp;

    private Long modifyEmp;

    /**
     * 折扣(以 x.xx 的形式使用，直接与原价相乘)
     **/
    @Min(value = 0, message = "折扣不能小于0")
    @Max(value = 1, message = "折扣不能大于1")
    private Double discount;

    @Override
    public String toString() {
        return "MemberGradeVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", minGrowth=" + minGrowth +
                ", maxGrowth=" + maxGrowth +
                ", createEmp=" + createEmp +
                ", modifyEmp=" + modifyEmp +
                ", discount=" + discount +
                '}';
    }

    public Long getCreateEmp() {
        return createEmp;
    }

    public void setCreateEmp(Long createEmp) {
        this.createEmp = createEmp;
    }

    public Long getModifyEmp() {
        return modifyEmp;
    }

    public void setModifyEmp(Long modifyEmp) {
        this.modifyEmp = modifyEmp;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setMinGrowth(Long minGrowth) {
        this.minGrowth = minGrowth;
    }

    public Long getMinGrowth() {
        return this.minGrowth;
    }

    public void setMaxGrowth(Long maxGrowth) {
        this.maxGrowth = maxGrowth;
    }

    public Long getMaxGrowth() {
        return this.maxGrowth;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getDiscount() {
        return this.discount;
    }

}
