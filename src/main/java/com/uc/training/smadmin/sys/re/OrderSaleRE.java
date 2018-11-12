package com.uc.training.smadmin.sys.re;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 订单销量RE
 *
 * @author 吴佰川（baichuan.wu@ucarinc.com）创建
 * @version 1.0
 * @date 2018/11/12 12:18
 */
public class OrderSaleRE implements Serializable {
    private static final long serialVersionUID = -4257150860810500954L;
    private BigDecimal january;
    private BigDecimal february;
    private BigDecimal march;
    private BigDecimal april;
    private BigDecimal may;
    private BigDecimal june;
    private BigDecimal july;
    private BigDecimal aguest;
    private BigDecimal september;
    private BigDecimal october;
    private BigDecimal november;
    private BigDecimal december;

    public BigDecimal getJanuary() {
        return january;
    }

    public void setJanuary(BigDecimal january) {
        this.january = january;
    }

    public BigDecimal getFebruary() {
        return february;
    }

    public void setFebruary(BigDecimal february) {
        this.february = february;
    }

    public BigDecimal getMarch() {
        return march;
    }

    public void setMarch(BigDecimal march) {
        this.march = march;
    }

    public BigDecimal getApril() {
        return april;
    }

    public void setApril(BigDecimal april) {
        this.april = april;
    }

    public BigDecimal getMay() {
        return may;
    }

    public void setMay(BigDecimal may) {
        this.may = may;
    }

    public BigDecimal getJune() {
        return june;
    }

    public void setJune(BigDecimal june) {
        this.june = june;
    }

    public BigDecimal getJuly() {
        return july;
    }

    public void setJuly(BigDecimal july) {
        this.july = july;
    }

    public BigDecimal getAguest() {
        return aguest;
    }

    public void setAguest(BigDecimal aguest) {
        this.aguest = aguest;
    }

    public BigDecimal getSeptember() {
        return september;
    }

    public void setSeptember(BigDecimal september) {
        this.september = september;
    }

    public BigDecimal getOctober() {
        return october;
    }

    public void setOctober(BigDecimal october) {
        this.october = october;
    }

    public BigDecimal getNovember() {
        return november;
    }

    public void setNovember(BigDecimal november) {
        this.november = november;
    }

    public BigDecimal getDecember() {
        return december;
    }

    public void setDecember(BigDecimal december) {
        this.december = december;
    }

    @Override
    public String toString() {
        return "OrderSaleRE{" +
                "january=" + january +
                ", february=" + february +
                ", march=" + march +
                ", april=" + april +
                ", may=" + may +
                ", june=" + june +
                ", july=" + july +
                ", aguest=" + aguest +
                ", september=" + september +
                ", october=" + october +
                ", november=" + november +
                ", december=" + december +
                '}';
    }
}
