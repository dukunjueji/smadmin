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

    private static RedissonManager redissonManager;

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
        synchronized (RedissonManager.class) {
            redissonManager = new RedissonManager();
            return redissonManager;
        }
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
