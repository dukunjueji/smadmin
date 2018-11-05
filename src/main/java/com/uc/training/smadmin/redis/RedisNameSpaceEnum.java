package com.uc.training.smadmin.redis;

import java.util.concurrent.TimeUnit;
/**
 * 命名空间枚举类
 *
 * @author zhongling(ling.zhong @ ucarinc.com)
 * @since 2018年10月31日 15:49+
 */
public enum RedisNameSpaceEnum {

    //商品详情缓存
    GOODS_DETAIL("goods", "商品详情缓存", 1, TimeUnit.DAYS),

    //验证码缓存
    SYS_CODE("sys", "验证码缓存", 3, TimeUnit.MINUTES);

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
