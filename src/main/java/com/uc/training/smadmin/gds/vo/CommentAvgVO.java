package com.uc.training.smadmin.gds.vo;

import java.io.Serializable;

/**
 * description: 评论总记录数和评分 平均数
 *
 * @author hhj
 * @version 1.0
 * @date 2018/11/20  10:54
 */
public class CommentAvgVO implements Serializable {
    private static final long serialVersionUID = -842880782486785731L;

    /**
     * 评论总记录数
     */
    private Integer count;

    /**
     * 好评总记录数
     */
    private Integer goodCount;

    /**
     * 中评总记录数
     */
    private Integer midCount;

    /**
     * 差评总记录数
     */
    private Integer badCount;

    /**
     *评分平均数
     */
    private Float sroceAvg;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Float getSroceAvg() {
        return sroceAvg;
    }

    public void setSroceAvg(Float sroceAvg) {
        this.sroceAvg = sroceAvg;
    }

    public Integer getGoodCount() {
        return goodCount;
    }

    public void setGoodCount(Integer goodCount) {
        this.goodCount = goodCount;
    }

    public Integer getMidCount() {
        return midCount;
    }

    public void setMidCount(Integer midCount) {
        this.midCount = midCount;
    }

    public Integer getBadCount() {
        return badCount;
    }

    public void setBadCount(Integer badCount) {
        this.badCount = badCount;
    }
}
