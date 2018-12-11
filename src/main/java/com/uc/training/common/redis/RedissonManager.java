package com.uc.training.common.redis;

import com.uc.training.common.utils.ThreadUtils;
import com.uc.training.common.utils.ValidatorUtils;
import com.zuche.redis.cluster.ClusterManager;
import com.zuche.redis.config.RedisReadWriteConfig;
import com.zuche.redis.pool.RedisPool;
import com.zuche.redis.pool.RedisPoolConfig;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.ClusterServersConfig;
import org.redisson.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

    private static List<RedisPoolConfig> configs;

    private static RedissonManager redissonManager = new RedissonManager();

    private RedissonManager() {
    }

    static {
        List<RedisReadWriteConfig> redisReadWriteConfigs = ClusterManager
                .getRedisReadWriteConfig(REDISSON_CACHE_GROUP);
        if (ValidatorUtils.isNotNull(redisReadWriteConfigs)) {
            if (configs == null) {
                configs = new ArrayList<>();
            }
            for (RedisReadWriteConfig redisReadWriteConfig : redisReadWriteConfigs) {
                if (ValidatorUtils.isNotNull(redisReadWriteConfig.getPools())) {
                    for (RedisPool redisPool : redisReadWriteConfig.getPools()) {
                        configs.add(redisPool.getConfig());
                    }
                }
            }
        }
    }

    public static RedissonManager getInstance() {
        if (redissonManager == null) {
            redissonManager = new RedissonManager();
        }

        return redissonManager;
    }

    private void init() {
        //ClusterManager.clusterModelHash(block.namespaces, block.keys, block.groupName,block.isRead);
    }


    private Config initConfig() {
        Config sessionConfig = new Config();
        Set<String> redisUris = new HashSet<String>();
        for (RedisPoolConfig redisPoolConfig : configs) {
            redisUris.add("redis://".concat(redisPoolConfig.getHost()).concat(":").concat(String.valueOf(redisPoolConfig.getPort())));
        }
        if (redisUris.size() == 1) {
            sessionConfig.useSingleServer().setAddress(redisUris.iterator().next());
        } else {
            ClusterServersConfig serversConfig = sessionConfig.useClusterServers().setScanInterval(2000);
            for (String redisUri : redisUris) {
                serversConfig.addNodeAddress(redisUri);
            }
        }
//                .setAddress(config.getHost())
//                //.setPassword(config.getPassword())
//                //.setDatabase(config.getDatabase())
//                .setConnectionPoolSize(config.getCorePoolSize());
        return sessionConfig;
    }

    private RedissonClient getRedissonClient() {
        logger.info("get RedissonClient ...");

        synchronized (RedissonManager.class) {
            if (redissonClient == null) {
                redissonClient = Redisson.create(initConfig());
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
        RedissonClient redissonClient = getRedissonClient();
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

    /**
     * 分布式加锁操作,使用默认时间和单位
     *
     * @param callback 回调函数，用于加锁后续处理
     * @param fairLock 是否使用公平锁
     */
    public <T> T lock(RedissonDistributeLockCallBack<T> callback, boolean fairLock) {
        return lock(callback, DEFAULT_EXPIRED_TIME, DEFAULT_TIME_UNIT, fairLock);
    }


    /**
     * 分布式加锁，使用自定义时间和单位
     *
     * @param callback  回调函数，用于加锁后续处理
     * @param leaseTime 过期时间
     * @param timeUnit  时间单位
     * @param fairLock  是否是公平
     */
    public <T> T lock(RedissonDistributeLockCallBack<T> callback, Long leaseTime, TimeUnit timeUnit,
                      boolean fairLock) {
        RLock lock = getLock(callback.getLockName(), fairLock);
        try {
            if (lock != null) {
                lock.lock(leaseTime, timeUnit);
                return callback.process();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (lock != null && lock.isLocked()) {
                lock.unlock();
            }
        }
        return null;
    }

    /**
     * 尝试加锁操作，使用默认的时间和单位
     *
     * @param callback 回调
     * @param fairLock 是否是公平锁
     * @param waitTime 等待时间
     */
    public <T> T tryLock(RedissonDistributeLockCallBack<T> callback, boolean fairLock, long waitTime) {
        return tryLock(callback, DEFAULT_EXPIRED_TIME, DEFAULT_TIME_UNIT, fairLock, waitTime);
    }

    /**
     * 尝试加锁操作
     *
     * @param callback  回调
     * @param leaseTime 锁持续时间
     * @param timeUnit  时间单位
     * @param fairLock  是否是公平锁
     * @param waitTime  等待时间
     */
    public <T> T tryLock(RedissonDistributeLockCallBack<T> callback, Long leaseTime, TimeUnit timeUnit,
                         boolean fairLock, long waitTime) {
        RLock lock = getLock(callback.getLockName(), fairLock);
        try {
            if (lock != null) {
                if (lock.tryLock(waitTime, leaseTime, timeUnit)) {
                    return callback.process();
                }
            }
        } catch (InterruptedException e) {
            logger.error("尝试锁失败" + e.getMessage(), e);
        } finally {
            if (lock != null && lock.isLocked()) {
                lock.unlock();
            }
        }
        return null;
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

    public static void main(String[] args) {
        RedissonManager.getInstance().lock(new RedissonDistributeLockCallBack<Object>() {
            @Override
            public Object process() {
                System.out.println("测试成功");

                return null;
            }
            @Override
            public String getLockName() {
                return "lock_01";
            }
        }, true);
    }

    public void testFairLock(){
        RedissonClient redisson = null;
        RLock lock = redisson.getFairLock("wewr");
        try {
            System.out.println("测试成功");
            lock.lock();
        } catch (Exception e) {
        } finally {
            lock.unlock();
        }
        RLock fairLock = redisson.getFairLock("anyLock");
        try{
            // 最常见的使用方法
            fairLock.lock();
            // 支持过期解锁功能, 10秒钟以后自动解锁,无需调用unlock方法手动解锁
            fairLock.lock(10, TimeUnit.SECONDS);
            // 尝试加锁，最多等待100秒，上锁以后10秒自动解锁
            boolean res = fairLock.tryLock(100, 10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            fairLock.unlock();
        }
    }

}
