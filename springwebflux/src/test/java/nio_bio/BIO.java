package nio_bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
//采用BIO的方式来维护和客户端的连接

/**
 * 为了避免资源耗尽，我们采用线程池的方式来处理读写服务。但是这么做依然有很明显的弊端：
 *
 * 1. 同步阻塞IO，读写阻塞，线程等待时间过长
 *
 * 2. 在制定线程策略的时候，只能根据CPU的数目来限定可用线程资源，不能根据连接并发数目来制定，
 * 也就是连接有限制。否则很难保证对客户端请求的高效和公平。
 *
 * 3. 多线程之间的上下文切换，造成线程使用效率并不高，并且不易扩展
 *
 * 4. 状态数据以及其他需要保持一致的数据，需要采用并发同步控制
 */
public class BIO {
    private static volatile AtomicInteger atomicInteger = new AtomicInteger();
    public static void main(String[] args){
//        主线程维护连接
        ExecutorService  executorService =
                new ThreadPoolExecutor(
                        5,
                        10,
                        5,
                        TimeUnit.MINUTES,
                        new LinkedBlockingQueue<Runnable>(),
                        new ThreadFactory() {
                            //每当线程池需要一个线程时，都是通过线程工厂创建的线程。
                            // 默认的线程工厂方法将创建一个新的、非守护的线程，
                            // 并且不包含特殊的线程信息。当然可以通过线程工厂定制线程的信息
                            @Override
                            public Thread newThread(Runnable r) {
                                Thread thread = new Thread(r);
                                thread.setName("t" + atomicInteger.incrementAndGet());
                                thread.setDaemon(true);// 设置为守护线程
                                return thread;
                            }
                        },
                        new ThreadPoolExecutor.CallerRunsPolicy()
        );
        ServerSocket server;
        try {
            server = new ServerSocket(8001);
            System.out.println("监听建立 等你上线\n");
            while (true) {
                Socket socket = server.accept();
                System.out.println("建立了链接\n");
                executorService.submit(new Handler(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
//处理读写服务
class Handler implements Runnable {
    Socket socket;

    public Handler(Socket socket) {
        this.socket = socket;
    }
    public void run() {
        try {
            //获取Socket的输入流，接收数据
            BufferedReader buf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String readData = buf.readLine();
            while (readData != null) {
                readData = buf.readLine();
                System.out.println(readData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
