package com.uc.training.smadmin.redis;

import com.ycc.tools.middleware.redis.RedisCacheUtils;

/**
 * 请填写类注释
 *
 * @author zhongling(ling.zhong @ ucarinc.com)
 * @since 2018年10月31日 16:35
 */
public class RedisUtil {
    public static void main(String[] args) {
        RedisCacheUtils redis = RedisCacheUtils.getInstance(RedisConfigEnum.GOODS_DETAIL);
        redis.set("hello","world");
        System.out.println(redis.get("hello"));
    }
}
