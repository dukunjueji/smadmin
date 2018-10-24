package com.uc.training.smadmin.utils;

import com.uc.training.common.enums.UUIDTypeEnum;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
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

    private static volatile long lastSecond;//记录上一秒的时间戳
    public static BlockingQueue<UUIDData> queue;//堵塞队列，使用FIFO策略存储获取流水线号
    public static AtomicInteger count;//流水线号 原子操作

    /**
     * 通过编号类型获取uuid：uuid=编号类型+时间戳+流水线号
     * @param uuidType
     * @return
     */
    public static String getUuidByType(String uuidType){
        StringBuffer uuid=new StringBuffer();
        String typeValue=null;
        for(UUIDTypeEnum typeEnum : UUIDTypeEnum.values()){
            if(uuidType.equals(typeEnum.getType())){
                typeValue=typeEnum.getValue();
                break;
            }
        }
        //参数：类型有错
        if(typeValue==null){
            return null;
        }
        uuid.append(typeValue);
        Long timestamp=System.currentTimeMillis()/1000;
        uuid.append(timestamp);

        //TODO:消费数据，低于 UUIDTypeEnum.MINQUEUESIZE，开始生成数据
        if(queue==null){
            queue=new ArrayBlockingQueue<UUIDData>(UUIDTypeEnum.QUEUESIZE,true);
            count= new AtomicInteger(0);// 流水线号 初始化从1开始
            lastSecond=System.currentTimeMillis()/1000;
        }else{
            long currentTime=System.currentTimeMillis()/1000;
            if(currentTime-lastSecond > 1){//新的一秒，重新初始化
                queue=new ArrayBlockingQueue<UUIDData>(UUIDTypeEnum.QUEUESIZE,true);
                count= new AtomicInteger(0);// 流水线号 初始化从1开始
                lastSecond=System.currentTimeMillis()/1000;
            }
        }
        int sort=GetAndProductUUID.consumer();
        uuid.append(sort);
        return uuid.toString();
    }
    public static void main(String[] args) {

        String uuid=UUIDUtil.getUuidByType(UUIDTypeEnum.GOODSID.getType());
        System.out.println("------------------------"+uuid+"------------------------------------------------");
    }
}
class GetAndProductUUID {

    private static final Lock lock = new ReentrantLock();
    private static boolean isRunning=true;//控制生产者线程是否退出

    /**
     * 消费者，获取队列中的流水线号
     * @return
     */
    public static Integer consumer() {
        try {
            if(UUIDUtil.queue.size() < UUIDTypeEnum.MINQUEUESIZE) {
                Thread thread=new Producer();
                thread.start();
            }
            lock.lock();
            UUIDData data = UUIDUtil.queue.take();
            return data.getData();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
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
        private void producer() {
            UUIDData data = null;
            while(isRunning) {
                try {
                    if(UUIDUtil.queue.size()>UUIDTypeEnum.ONEPRODUCTQUEUESIZE){
                        isRunning=false;
                    }
                    data = new UUIDData(UUIDUtil.count.incrementAndGet());
                    System.out.println("生产了数据"+data.getData());
                    UUIDUtil.queue.offer(data,1,TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
class UUIDData {
    private final int data;//存储水流线号
    public UUIDData(int d){
        data = d;
    }
    public UUIDData(String d){
        data = Integer.valueOf(d);
    }
    public int getData(){
        return data;
    }
}