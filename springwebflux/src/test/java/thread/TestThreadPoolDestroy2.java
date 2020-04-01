package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
//线程池自身携带的方法实现:  shuwdown后立即调用awaitTermination 实现
public class TestThreadPoolDestroy2 {
    public static void main(String[] args) {
        TestPoolDestroy();
        System.out.println("main end");
    }

    private static void TestPoolDestroy() {
        ExecutorService batchTaskPool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3; i++) {
            batchTaskPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName() + "进入run");
                        Thread.sleep(5 * 1000);
                        System.out.println(Thread.currentThread().getName() + "退出run");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        try {
            batchTaskPool.shutdown();
            batchTaskPool.awaitTermination(1, TimeUnit.DAYS);
            System.out.println("三个都执行完毕");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
