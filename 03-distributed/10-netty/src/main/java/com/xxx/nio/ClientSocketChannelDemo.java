package com.xxx.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class ClientSocketChannelDemo {

    public static class TCPEchoClient implements Runnable {

        // 客户端线程名称
        private String name;
        private Random random = new Random();
        // 服务器端IP和端口
        private InetSocketAddress remoteAddress;

        public TCPEchoClient(String name, InetSocketAddress remoteAddress) {
            this.name = name;
            this.remoteAddress = remoteAddress;
        }

        @Override
        public void run() {

            // 创建解码器
            Charset utf8 = Charset.forName("UTF-8");
            Selector selector;
            try {
                // 创建TCP通道
                SocketChannel sc = SocketChannel.open();
                // 设置通道为非阻塞
                sc.configureBlocking(false);

                // 创建选择器
                selector = Selector.open();

                // 注册感兴趣的事件
                int interestSet = SelectionKey.OP_READ | SelectionKey.OP_WRITE;

                // 向选择器注册通道
                sc.register(selector, interestSet, new Buffers(256, 256));

                // 向服务器发起链接，一条通道代表一条TCP链接
                sc.connect(remoteAddress);

                // 等待三次握手完成
                while (!sc.finishConnect()) {
                }
                System.out.println(name + " finished connection");
            } catch (Exception e) {
                System.out.println("client connect failed");
                return;
            }

            // 与服务器断开或者线程被中断则结束线程
            try {
                int i = 1;
                while (!Thread.currentThread().isInterrupted()) {
                    // 阻塞等待
                    selector.select();

                    // 每一个key代表一个通道
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();

                    // 遍历已就绪的事件，处理这个通道的已就绪事件
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        // 防止下次select方法返回已处理的通道
                        iterator.remove();

                        // 通过SelectKey获取通道对应的缓冲区
                        Buffers buffers = (Buffers) key.attachment();
                        ByteBuffer readBuffer = buffers.getReadBuffer();
                        ByteBuffer writeBuffer = buffers.getWriteBuffer();

                        // 通过SelectionKey获取通道对应的缓冲区
                        SocketChannel sc = (SocketChannel) key.channel();

                        // 表示底层socket的读缓冲区有数据可读
                        if (key.isReadable()) {
                            // 从底层socket读缓冲区中读入的数据
                            sc.read(readBuffer);
                            readBuffer.flip();
                            // 字节解码
                            CharBuffer cb = utf8.decode(readBuffer);
                            // 显示由服务器端发送的消息
                            System.out.println(cb.array());
                            readBuffer.clear();
                        }
                        // 表示底层socket的写缓冲区有空间可写
                        if (key.isWritable()) {
                            writeBuffer.put((name + " " + i).getBytes("UTF-8"));
                            writeBuffer.flip();
                            // 将数据写入到socket的写缓冲区
                            sc.write(writeBuffer);
                            writeBuffer.clear();
                            i++;
                        }
                    }
                    Thread.sleep(1000 + random.nextInt(1000));
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    selector.close();
                } catch (IOException e) {
                    System.out.println(name + " close selector failed");
                } finally {
                    System.out.println(name + " closed");
                }
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        InetSocketAddress remoteAddress = new InetSocketAddress("127.0.0.1", 8080);

        Thread ta = new Thread(new TCPEchoClient("thread a", remoteAddress));
        Thread tb = new Thread(new TCPEchoClient("thread b", remoteAddress));
        Thread tc = new Thread(new TCPEchoClient("thread c", remoteAddress));
        Thread td = new Thread(new TCPEchoClient("thread d", remoteAddress));

        ta.start();
        tb.start();
        tc.start();

        Thread.sleep(5000);

        /*结束客户端a*/
        ta.interrupt();

        /*开始客户端d*/
        td.start();
    }
}
