package com.uc.training.smadmin.redis;

import java.util.concurrent.TimeUnit;

public enum RedisNameSpaceEnum {

    //商品详情缓存
    GOODS_DETAIL("goods", "商品详情缓存", 1, TimeUnit.DAYS),

    //验证码缓存
    SYS_CODE("sys", "验证码缓存", 1, TimeUnit.MINUTES);

    /**
     * 前缀
     */
    private String pre;
    /**
     * 描述
     */
    private String discrible;
    /**
     * 超时设置
     */
    private long timeout;
    /**
     * 超时单位
     */
    private TimeUnit unit;

    RedisNameSpaceEnum(String pre, String discrible, long timeout, TimeUnit unit) {
        this.pre = pre;
        this.discrible = discrible;
        this.timeout = timeout;
        this.unit = unit;
    }

    public String getPre() {
        return pre;
    }


    public String getDiscrible() {
        return discrible;
    }

    public long getTimeout() {
        return timeout;
    }

    public TimeUnit getUnit() {
        return unit;
    }
}
