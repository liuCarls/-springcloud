package nio_bio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 基于Reactor Pattern 处理模式中，定义以下三种角色:
 *  Reactor 将I/O事件分派给对应的Handler
 *  Acceptor 处理客户端新连接，并分派请求到处理器链中
 *  Handlers 执行非阻塞读/写 任务
 *
 *  参见Reactor模型：https://blog.csdn.net/bingxuesiyang/article/details/89888664
 */
public class NIOServer {
    public static void main(String[] args) {
        Selector selector;
        int port = 8082;
        try {
            selector = Selector.open();
            ServerSocketChannel serverSocket = ServerSocketChannel.open();
            serverSocket.socket().bind(new InetSocketAddress(port));
            serverSocket.configureBlocking(false);
            serverSocket.register(selector, SelectionKey.OP_ACCEPT);
            new Thread(new Handler2(selector)).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
class Handler2 implements Runnable {
     Selector selector;
    Handler2(Selector selector) {
        this.selector = selector;
    }
     @Override
     public void run() {
         while (!Thread.interrupted()) {
             try {
                 //阻塞等待事件
                 selector.select();
                 // 事件列表
                 Set selected = selector.selectedKeys();
                 Iterator it = selected.iterator();
                 while (it.hasNext()) {
                     it.remove();
                     //分发事件
                     dispatch((SelectionKey) (it.next()));
                 }
             } catch (Exception e) {
             }
         }
     }
     private void dispatch(SelectionKey key) throws Exception {
         if (key.isAcceptable()) {
             register(key);//新链接建立，注册
         } else if (key.isReadable()) {
             read(key);//读事件处理
         } else if (key.isWritable()) {
             write(key);//写事件处理
         }
     }
     private void register(SelectionKey key) throws Exception {
         ServerSocketChannel server = (ServerSocketChannel) key
                 .channel();
         // 获得和客户端连接的通道
         SocketChannel channel = server.accept();
         channel.configureBlocking(false);
         //客户端通道注册到selector 上
         channel.register(this.selector, SelectionKey.OP_READ);
     }
     private void read(SelectionKey key) throws Exception {

     }
      private void write(SelectionKey key) throws Exception {

     }
 }