package com.lgx.test.thread;

import java.util.concurrent.*;

public class ThreadPoolTest {
    public static void main(String[] args){
        /**
         * Executors是一个线程池工厂,提供了很多的工厂方法来创建线程池，
         * 但这种方法限制了线程池的功能，还是需要我们通过更底层的方式来创建线程池。
         * // 创建单一线程的线程池
         * public static ExecutorService newSingleThreadExecutor();
         * // 创建固定数量的线程池
         * public static ExecutorService newFixedThreadPool(int nThreads);
         * // 创建带缓存的线程池
         * public static ExecutorService newCachedThreadPool();
         * // 创建定时调度的线程池
         * public static ScheduledExecutorService newScheduledThreadPool(int corePoolSize);
         * // 创建流式（fork-join）线程池
         * public static ExecutorService newWorkStealingPool();
         *
         */
//        ExecutorService executor = Executors.newFixedThreadPool(5);
//        for (int i = 0; i < 10; i++) {
//            executor.submit(() -> {
//                System.out.println("thread id is: " + Thread.currentThread().getId());
//                try {
//                    Thread.sleep(1000L);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            });
//        }
//        executor.shutdown();
        /**
         *  ThreadPoolExecutor
         *  public ThreadPoolExecutor(int corePoolSize,
         *                            int maximumPoolSize,
         *                            long keepAliveTime,
         *                            TimeUnit unit,
         *                            BlockingQueue<Runnable> workQueue,
         *                            ThreadFactory threadFactory,
         *                            RejectedExecutionHandler handler);
         *  corePoolSize，线程池中的核心线程数
         *  maximumPoolSize，线程池中的最大线程数
         *  keepAliveTime，空闲时间，当线程池数量超过核心线程数时，多余的空闲线程存活的时间，即：这些线程多久被销毁。
         *  unit，空闲时间的单位，可以是毫秒、秒、分钟、小时和天，等等
         *  workQueue，等待队列，线程池中的线程数超过核心线程数时，任务将放在等待队列，它是一个BlockingQueue类型的对象
         *  threadFactory，线程工厂，我们可以使用它来创建一个线程
         *  handler，拒绝策略，当线程池和等待队列都满了之后，需要通过该对象的回调函数进行回调处理
         *
         *  1. 等待队列-workQueue
         *  等待队列是BlockingQueue类型的，理论上只要是它的子类，我们都可以用来作为等待队列。
         *
         *  同时，jdk内部自带一些阻塞队列，我们来看看大概有哪些。
         *
         *  ArrayBlockingQueue，队列是有界的，基于数组实现的阻塞队列
         *  LinkedBlockingQueue，队列可以有界，也可以无界。基于链表实现的阻塞队列
         *  SynchronousQueue，不存储元素的阻塞队列，每个插入操作必须等到另一个线程调用移除操作，否则插入操作将一直处于阻塞状态。该队列也是Executors.newCachedThreadPool()的默认队列
         *  PriorityBlockingQueue，带优先级的无界阻塞队列
         *  通常情况下，我们需要指定阻塞队列的上界（比如1024）。另外，如果执行的任务很多，我们可能需要将任务进行分类，然后将不同分类的任务放到不同的线程池中执行。
         *
         *  2. 线程工厂-threadFactory
         *  ThreadFactory是一个接口，只有一个方法。
         * Executors的实现使用了默认的线程工厂-DefaultThreadFactory。
         *  它的实现主要用于创建一个线程，线程的名字为pool-{poolNum}-thread-{threadNum}。
         *  很多时候，我们需要自定义线程名字。我们只需要自己实现ThreadFactory，用于创建特定场景的线程即可。
         *
         *  3. 拒绝策略-handler
         *  所谓拒绝策略，就是当线程池满了、队列也满了的时候，我们对任务采取的措施。或者丢弃、或者执行、或者其他...
         *
         *  jdk自带4种拒绝策略，我们来看看。
         *
         *  CallerRunsPolicy // 在调用者线程执行
         *  AbortPolicy // 直接抛出RejectedExecutionException异常
         *  DiscardPolicy // 任务直接丢弃，不做任何处理
         *  DiscardOldestPolicy // 丢弃队列里最旧的那个任务，再尝试执行当前任务
         *  这四种策略各有优劣，比较常用的是DiscardPolicy，但是这种策略有一个弊端就
         *  是任务执行的轨迹不会被记录下来。所以，我们往往需要实现自定义的拒绝策略，
         *  通过实现RejectedExecutionHandler接口的方式。
         * 4.提交任务的几种方式
         * 往线程池中提交任务，主要有两种方法，execute()和submit()。
         * execute()用于提交不需要返回结果的任务，我们看一个例子。
         * public static void main(String[] args) {
         *     ExecutorService executor = Executors.newFixedThreadPool(2);
         *     executor.execute(() -> System.out.println("hello"));
         * }
         * submit()用于提交一个需要返回果的任务。该方法返回一个Future对象，
         * 通过调用这个对象的get()方法，我们就能获得返回结果。get()方法会一直阻塞，
         * 直到返回结果返回。另外，我们也可以使用它的重载方法get(long timeout, TimeUnit unit)，
         * 这个方法也会阻塞，但是在超时时间内仍然没有返回结果时，将抛出异常TimeoutException。
         * public static void main(String[] args) throws Exception {
         *     ExecutorService executor = Executors.newFixedThreadPool(2);
         *     Future<Long> future = executor.submit(() -> {
         *         System.out.println("task is executed");
         *         return System.currentTimeMillis();
         *     });
         *     System.out.println("task execute time is: " + future.get());
         * }
         *
         * 5关闭线程池
         * 在线程池使用完成之后，我们需要对线程池中的资源进行释放操作，这就涉及到关闭功能。我们可以调用线程池对象的shutdown()和shutdownNow()方法来关闭线程池。
         *
         * 这两个方法都是关闭操作，又有什么不同呢？
         *
         * shutdown()会将线程池状态置为SHUTDOWN，不再接受新的任务，同时会等待线程池中已有的任务执行完成再结束。
         * shutdownNow()会将线程池状态置为SHUTDOWN，对所有线程执行interrupt()操作，清空队列，并将队列中的任务返回回来。
         * 另外，关闭线程池涉及到两个返回boolean的方法，isShutdown()和isTerminated，分别表示是否关闭和是否终止。
         *
         */
        System.out.println("number of cpu:"+Runtime.getRuntime().availableProcessors());
//        ExecutorService executor1 = new ThreadPoolExecutor(
        ThreadPoolExecutor executor1 = new ThreadPoolExecutor(
                1,
                1,
                1,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1)) {
            @Override protected void beforeExecute(Thread t, Runnable r) {
//                System.out.println("beforeExecute is called");
            }
            @Override protected void afterExecute(Runnable r, Throwable t) {
//                System.out.println("afterExecute is called");
            }
            @Override protected void terminated() {
//                System.out.println("terminated is called");
            }
        };
        executor1.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
//        executor.submit(() -> System.out.println("this is a task"));
        for (int i = 0; i < 10; i++) {
            Future future = executor1.submit(new DivTask(100, i));
            executor1.execute(new DivTask("task-"+i));
            //在使用submit()的时候一定要注意它的返回对象Future，为了避免任务执行异常被吞掉的问题，
            //我们需要调用Future.get()方法。使用execute()将不会出现这种问题。
            try {
                future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        executor1.shutdown();

//        ThreadPoolExecutor
    }

    static class DivTask implements Runnable {
        int a, b;
        String name;

        public DivTask(int a, int b) {
            this.a = a;
            this.b = b;
        }

        public DivTask(String name) {
            this.name = name;
        }
        @Override
        public void run() {
            try {
                System.out.println(this.name + " is running.");
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (a != 0) {
                double result = a / b;
                System.out.println(result);
            }
        }
    }
}

