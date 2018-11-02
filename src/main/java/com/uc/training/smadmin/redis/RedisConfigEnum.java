package com.uc.training.smadmin.redis;

import com.uc.training.smadmin.redis.constant.RedisConfigConstant;
import com.ycc.tools.middleware.redis.IRedisConfig;

import java.util.concurrent.TimeUnit;


/**
 * redis配置枚举类
 *
 * @author zhongling(ling.zhong @ ucarinc.com)
 * @since 2018年10月31日 15:49
 */
public enum RedisConfigEnum implements IRedisConfig {

    /**
     * 商品详情缓存
     */
    GOODS_DETAIL(RedisConfigConstant.GROUPNAME,RedisConfigConstant.NAMESPACE_ORDER_DETAIL, true, RedisNameSpaceEnum.GOODS_DETAIL.getTimeout(), RedisNameSpaceEnum.GOODS_DETAIL.getUnit()),

    /**
     * 验证码缓存
     */
    SYS_CODE(RedisConfigConstant.GROUPNAME,RedisConfigConstant.NAMESPACE_ORDER_DETAIL, true, RedisNameSpaceEnum.SYS_CODE.getTimeout(), RedisNameSpaceEnum.SYS_CODE.getUnit());

    /** 集群名*/
    private String groupName;
    /** 命名空间 */
    private int nameSpace;
    /** 默认开关 */
    private Boolean defaultStatus;
    /** 有效时间 */
    private long timeout;
    /** 有效时间单位 */
    private TimeUnit unit;

    RedisConfigEnum(String groupName, int nameSpace, Boolean defaultStatus, long timeout, TimeUnit unit) {
        this.groupName = groupName;
        this.nameSpace = nameSpace;
        this.defaultStatus = defaultStatus;
        this.timeout = timeout;
        this.unit = unit;
    }

    @Override
    public String getName() {
        return this.name();
    }

    @Override
    public String getGroupName() {
        return groupName;
    }

    @Override
    public int getNameSpace() {
        return this.nameSpace;
    }

    @Override
    public Boolean getDefaultStatus() {
        return this.defaultStatus;
    }

    @Override
    public long getTimeout() {
        return this.timeout;
    }

    @Override
    public TimeUnit getUnit() {
        return this.unit;
    }
}
