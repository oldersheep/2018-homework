package com.xxx.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class ServerSocketChannelDemo {

    public static class TCPEchoServer implements Runnable {

        private InetSocketAddress localAddress;

        public TCPEchoServer(int port) {
            this.localAddress = new InetSocketAddress(port);
        }
        @Override
        public void run() {
            Charset utf8 = Charset.forName("UTF-8");

            ServerSocketChannel ssc = null;
            Selector selector = null;

            Random random = new Random();

            try {
                // 创建选择器
                selector = Selector.open();

                // 创建服务器通道
                ssc = ServerSocketChannel.open();
                // 设置通道为非阻塞
                ssc.configureBlocking(false);
                // 设置监听服务器端口，设置最大连接缓冲数为100
                ssc.bind(localAddress, 100);

                // 注册事件，服务器通道只能对tcp的链接事件感兴趣
                ssc.register(selector, SelectionKey.OP_ACCEPT);
            } catch (Exception e) {
                System.out.println("server start failed");
                e.printStackTrace();
            }
            System.out.println("server start with address:" + localAddress);

            // 服务器线程被中断后会退出
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    int count = selector.select();
                    if (count == 0) continue;

                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    SelectionKey key = null;
                    while (iterator.hasNext()) {
                        key = iterator.next();
                        // 防止下次select方法返回已处理的通道
                        iterator.remove();
                        /** 若发现异常，则是客户端的问题，服务端要继续工作*/
                        try {
                            // scc 通道只能对链接事件感兴趣
                            if (key.isAcceptable()) {
                                // accept方法会返回一个普通通道，每个通道在内核中都对应一个socket缓存区
                                SocketChannel sc = ssc.accept();
                                sc.configureBlocking(false);

                                // 向选择器注册这个通道和普通通道感兴趣的事件。同时提供这个新通道相关的缓冲区
                                sc.register(selector, SelectionKey.OP_READ, new Buffers(256, 256));

                                System.out.println("accept from " + sc.getRemoteAddress());
                            }

                            // 普通通道感兴趣读事件且有数据可读
                            if (key.isReadable()) {
                                // 通过SelectKey获取通道对应的缓冲区
                                Buffers buffers = (Buffers)key.attachment();
                                ByteBuffer readBuffer = buffers.getReadBuffer();
                                ByteBuffer writeBuffer = buffers.getWriteBuffer();

                                // 通过SelectKey获取对应的通道
                                SocketChannel sc = (SocketChannel) key.channel();

                                // 从底层socket读缓冲区中读入的数据
                                sc.read(readBuffer);
                                readBuffer.flip();

                                // 解码显示，客户端发来的信息
                                CharBuffer cb = utf8.decode(readBuffer);
                                System.out.println(cb.array());

                                readBuffer.rewind();

                                // 准备好向客户端发送信息
                                // 先写入echo，再写入收到的信息
                                writeBuffer.put("echo from service".getBytes("UTF-8"));
                                writeBuffer.put(readBuffer);

                                readBuffer.clear();

                                // 设置通道写事件
                                key.interestOps(key.interestOps() | SelectionKey.OP_WRITE);
                            }

                            // 通道感兴趣写事件且底层缓冲区有空闲空间
                            if (key.isWritable()) {
                                Buffers buffers = (Buffers)key.attachment();
                                ByteBuffer writeBuffer = buffers.getWriteBuffer();
                                writeBuffer.flip();

                                SocketChannel sc = (SocketChannel)key.channel();

                                int len = 0;
                                while (writeBuffer.hasRemaining()) {
                                    len = sc.write(writeBuffer);
                                    // 说明底层缓冲区已满
                                    if (len == 0) break;
                                }
                                writeBuffer.compact();

                                // 说明数据全部写入到底层的socket写缓冲区
                                if (len != 0) {
                                    // 取消通道写事件
                                    key.interestOps(key.interestOps() & (~SelectionKey.OP_WRITE));
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("service encounter client error");
                            key.cancel();
                            key.channel().close();
                        }
                    }
                    Thread.sleep(random.nextInt(500));
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    selector.close();
                } catch (IOException e) {
                    System.out.println("selector close failed");
                } finally {
                    System.out.println("server closed");
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new TCPEchoServer(8080));
        thread.start();
        Thread.sleep(100000);
        /*结束服务器线程*/
        thread.interrupt();

    }
}
