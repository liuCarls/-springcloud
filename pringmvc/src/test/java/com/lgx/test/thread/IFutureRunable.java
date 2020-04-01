package com.lgx.test.thread;

import javafx.concurrent.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * FutureTask 实现了RunnableFuture接口，RunnableFuture接口集成了Runnable和Future接口。
 * new FutureTask执行里面的方法run()，run()方法中调用callable的call()方法，
 * 得到返回值result，调用set(result)将结果赋值给outcome变量；调用FutureTask的get()方法，
 * get()方法中先判断任务状态，如果小于completing，调用awitdone接口，阻塞等待完成
 *
 * https://www.cnblogs.com/xiaoxi/p/8303574.html
 *
 *
 * Callable是Executor框架中的功能类，一般情况下是配合 ExecutorService 来使用的，
 * 在ExecutorService接口中声明了若干个submit方法的重载版本，可以接受Runnable类。
 */
public class IFutureRunable {
    static IFutureRunable thiz = new IFutureRunable();
    public static void main(String[] args) {
        thiz.test1();
    }


    private void test() {
        // Task, Work
        Task task = new Task() {
            @Override
            protected Object call() throws Exception {
                return null;
            }
        };
    }

    private void test1() {
//        Runnable task = new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("do runnable");
//            }
//        };
        Callable task = new Callable() {
            @Override
            public Object call() throws Exception {
                //执行耗时的任务
                System.out.println("threadId:"+Thread.currentThread().getId());
                Thread.sleep(1000*10);
                return "ABCD";
            }
        };
        //创建线程池
        ExecutorService executor = Executors.newCachedThreadPool();
        List<Future> rs = new ArrayList();
        for (int i = 0; i < 10; i++) {
            rs.add(executor.submit(task));

        }
        for (Future r : rs) {
            try {
                System.out.println(r.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        }
        executor.shutdown();
    }


    public void test2() {
        Callable task = new Callable() {
            @Override
            public Object call() throws Exception {
                //执行耗时的任务
                System.out.println("threadId:"+Thread.currentThread().getId());
                Thread.sleep(1000*10);
                return "ABCD";
            }
        };
        FutureTask ft = new FutureTask(task);
        ExecutorService es = Executors.newCachedThreadPool();
        es.submit(ft);
        try {
            System.out.println(ft.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
