package com.uc.training.common.redis;

/**
 * @Description TODO 分布式锁回调接口
 * @Author Caizy
 * @Date 2018/10/25 13:40
 **/
public interface RedissonDistributeLockCallBack<T> {

    /**
     * 处理后续业务逻辑
     * @return
     */
    public T process();

    /**
     * 获取锁名字
     * @return
     */
    public String getLockName();
}
