package thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池中的线程如果不调用shutdown()方法线程是不会销毁的，即使是方法内
 * 部的局部变量线程池也不会销毁；调用shutdown方法之后在所有线程执行完后
 * 会线程线程池中的线程。所以在使用线程池的时候如果是方法内部使用一定要
 * shutdown销毁线程，如果是全局使用的静态线程池可以不shutdown。
 */
//如果想要判断线程池中的线程是否执行完毕，或者在多个线程在线程池中执行完毕之后处理某些事情可以结合闭锁来
//线程池不允许使用 Executors 去创建，而是通过 ThreadPoolExecutor 的方式
public class TestThreadPoolDestroy {
    public static void main(String[] args) {
        TestPoolDestroy();
        System.out.println("main end");
    }

    private static void TestPoolDestroy() {
        ExecutorService batchTaskPool = Executors.newFixedThreadPool(3);
        final CountDownLatch latch = new CountDownLatch(3);// 闭锁
        for (int i = 0; i < 3; i++) {
            batchTaskPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName() + "进入run");
                        Thread.sleep(5 * 1000);
                        System.out.println(Thread.currentThread().getName() + "退出run");
                        latch.countDown();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        try {
            latch.await();// 闭锁产生同步效果
            System.out.println("三个都执行完毕");
            // batchTaskPool.shutdown();// 调用此方法等待执行完毕会销毁线程，如果不调用此方法即使方法退出也不会销毁线程
            System.out.println(batchTaskPool.isTerminated());
            System.out.println(batchTaskPool.isShutdown());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
