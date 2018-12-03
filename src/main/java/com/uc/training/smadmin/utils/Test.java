package com.uc.training.smadmin.utils;

import com.uc.training.common.enums.UUIDTypeEnum;
import io.netty.util.concurrent.DefaultThreadFactory;

import java.util.concurrent.*;

/**
 * 请填写类注释
 *
 * @author zhongling(ling.zhong @ ucarinc.com)
 * @since 2018年11月14日 14:10
 */
public class Test {
    public static void main(String[] args) {
        ExecutorService service = new ThreadPoolExecutor(20,60,
        10L,TimeUnit.SECONDS,new ArrayBlockingQueue(50),Executors.defaultThreadFactory()
        );
        for(int i = 0; i < 50; i++) {
            Thread thread = new Thread(new UUIDThread());
            service.submit(thread);
        }
    }
}
class UUIDThread implements Runnable{

    @Override
    public void run() {
        String uuid = UUIDUtil.getUuidByType(UUIDTypeEnum.GOODSID.getType());
        System.out.println(Thread.currentThread().getId()+"：---------------------" + uuid);
    }
}
