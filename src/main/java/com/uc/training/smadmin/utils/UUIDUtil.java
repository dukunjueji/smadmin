package com.uc.training.smadmin.utils;

import com.uc.training.common.enums.UUIDTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生成各类型编号工具类
 *
 * @author zhongling(ling.zhong @ ucarinc.com)
 * @since 2018年10月15日 15:04
 */
public class UUIDUtil {
    protected final static Logger LOGGER = LoggerFactory.getLogger(UUIDUtil.class);
    /**
     * 记录上一秒的时间戳
     */
    private static volatile long lastSecond = System.currentTimeMillis() / 1000;
    /**
     * 堵塞队列，使用FIFO策略存储获取流水线号
     */
    static BlockingQueue<UUIDData> queue = new ArrayBlockingQueue<UUIDData>(UUIDTypeEnum.QUEUESIZE, true);
    /**
     * 流水线号 原子操作
     */
    static volatile AtomicInteger count = new AtomicInteger(0);

    /**
     * 通过编号类型获取uuid：uuid=编号类型+时间戳+流水线号
     *
     * @param uuidType
     * @return
     */
    public static String getUuidByType(String uuidType) {
        StringBuffer uuid = new StringBuffer();
        String typeValue = null;
        for(UUIDTypeEnum typeEnum : UUIDTypeEnum.values()) {
            if(uuidType.equals(typeEnum.getType())) {
                typeValue = typeEnum.getValue();
                break;
            }
        }
        //参数：类型有错
        if(typeValue == null) {
            return null;
        }
        uuid.append(typeValue);
        Long timestamp = System.currentTimeMillis() / 1000;
        uuid.append("-").append(timestamp).append("-");

        //消费数据，低于 UUIDTypeEnum.MINQUEUESIZE，开始生成数据
        long currentTime = System.currentTimeMillis() / 1000;
        //新的一秒，重新初始化
        if(currentTime - lastSecond > 1) {
            queue = new ArrayBlockingQueue<UUIDData>(UUIDTypeEnum.QUEUESIZE, true);
            // 流水线号 初始化从1开始
            count = new AtomicInteger(0);

            lastSecond = currentTime;
        }
        int sort = GetAndProductUUID.consumer();
        uuid.append(sort);
        return uuid.toString();
    }

    public static void main(String[] args) {
        String uuid = UUIDUtil.getUuidByType(UUIDTypeEnum.GOODSID.getType());
//        System.out.println("------------------------" + uuid + "------------------------------------------------");
    }
}

class GetAndProductUUID {

    private static final Lock LOCK = new ReentrantLock();
    /**
     * 消费者，获取队列中的流水线号
     *
     * @return
     */
    public static Integer consumer() {
        try {
            if(UUIDUtil.queue.size() < UUIDTypeEnum.MINQUEUESIZE) {
                Thread thread = new Producer();
                thread.start();
            }
            LOCK.lock();
            UUIDData data = UUIDUtil.queue.take();
            return data.getData();
        } catch (InterruptedException e) {
            UUIDUtil.LOGGER.error("获取队列中的流水线号失败:"+e.getMessage());
        } finally {
            LOCK.unlock();
        }
        return null;
    }

    /**
     * 生产者线程
     */
    static class Producer extends Thread {
        @Override
        public void run() {
            producer();
        }
        /**
         * 控制生产者线程是否退出
         */
        private boolean isRunning = true;

        private void producer() {
            UUIDData data = null;
            while(isRunning) {
                try {
                    if(UUIDUtil.queue.size() >= UUIDTypeEnum.ONEPRODUCTQUEUESIZE) {
                        isRunning = false;
                        break;
                    }
                    data = new UUIDData(UUIDUtil.count.incrementAndGet());
                    boolean status = UUIDUtil.queue.offer(data, 1, TimeUnit.SECONDS);
                    while(!status) {
                        status = UUIDUtil.queue.offer(data, 1, TimeUnit.SECONDS);
                    }
                } catch (InterruptedException e) {
                    UUIDUtil.LOGGER.error("生产者线程生产流水线号失败："+e.getMessage());
                }
            }
            isRunning = true;
        }
    }
}

class UUIDData {
    /**
     * 存储水流线号
     */
    private final Integer data;

    public UUIDData(int d) {
        data = d;
    }

    public int getData() {
        return data;
    }
}
