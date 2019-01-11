package com.ucar.smadmin.common.redis;

import com.ucar.smadmin.common.utils.ThreadUtils;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;


/**
 * @description: redisson管理类
 * @author: xuys
 * @date: 2018/8/23
 */
public class RedissonManager {
    private static final Logger logger = LoggerFactory.getLogger(RedissonManager.class);

    public static final String REDISSON_CACHE_GROUP = "smadmin_group";

    public static final Long TRY_TIMES = 3L;

    public static final Long DEFAULT_EXPIRED_TIME = 5L;

    public static final TimeUnit DEFAULT_TIME_UNIT = TimeUnit.SECONDS;

    private static RedissonClient redissonClient;

    private static RedissonManager redissonManager;

    private RedissonManager() {
    }


    public static RedissonManager getInstance() {
        synchronized (RedissonManager.class) {
            redissonManager = new RedissonManager();
            return redissonManager;
        }
    }

    private RedissonClient getRedissonClient() {
        logger.info("get RedissonClient ...");
        synchronized (RedissonManager.class) {
            if (redissonClient == null) {
                redissonClient = Redisson.create();
            }
        }
        return redissonClient;
    }

    /**
     * 获取锁，增加重试机制
     *
     * @param lockName
     * @return
     */
    public RLock getLock(String lockName, boolean fairLock) {
        RedissonConfig redissonConfig = new RedissonConfig();
        RedissonClient redissonClient = redissonConfig.getRedisson();
        //默认重试3次
        long tryTimes = TRY_TIMES;
        RLock lock = null;
        while (tryTimes > 0) {
            try {
                if (fairLock) {
                    lock = redissonClient.getFairLock(lockName);
                    break;
                } else {
                    lock = redissonClient.getLock(lockName);
                    break;
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), "获取锁失败 " + lockName + "\ttryTimes=" + tryTimes);
            }
            tryTimes--;
            ThreadUtils.sleep(1000L);
        }
        return lock;
    }

    public void closeRedissonClient(RedissonClient rc) {
        try {
            if (rc == null) {
                return;
            }
            rc.shutdown();
        } catch (Exception e) {
            logger.error("关闭Redis Client连接异常" + e.getMessage(), e);
        }
        logger.info("成功关闭Redis Client连接");
    }

    public static void main(String[] arg) {
        RLock lock = RedissonManager.getInstance().getLock("lock", true);
        try {
            lock.lock();
            System.out.println("测试成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
