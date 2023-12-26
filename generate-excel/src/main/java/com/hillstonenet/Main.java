package com.hillstonenet;

/**
 * @Author: Fang Jinxu
 * @Description:
 * @Date: 2023-12-20 10:57
 */
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        // 创建一个线程池
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                3, // 核心线程数
                10, // 最大线程数
                60, // 空闲线程存活时间
                TimeUnit.SECONDS, // 空闲线程存活时间的单位
                new LinkedBlockingQueue<Runnable>() // 工作队列
        );

        // 提交任务
        for (int i = 0; i < 10; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " is running");
                    try {
                        int count = 0;
                        while (true) {
                            if(count >=10) return;
                            Thread.sleep(1000);
                            count++;
                            System.out.println(Thread.currentThread().getName() + " count= "+count);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        // 关闭线程池
        executor.shutdown();
    }
}
