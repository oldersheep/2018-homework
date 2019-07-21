package com.xxx.hello;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public final class Server {

    public static void main(String[] args) throws Exception {
        //Configure the server
        // 创建两个EventLoopGroup对象
        // 创建boss线程组 用于服务端接收客户端的连接
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        // 创建worker线程组 用于SocketChannel的数据读写
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            // 创建ServerBootstrap对象
            ServerBootstrap b = new ServerBootstrap();
            // 设置使用的EventLoopGroup
            b.group(bossGroup, workerGroup)
                    // 设置要被实例化为 NioServerSocketChannel 类
                    .channel(NioServerSocketChannel.class)
                    // 设置NioServerSocketChannel的处理器
                    .handler(new LoggingHandler(LogLevel.INFO))
                    // 设置连入服务端的client的SocketChannel的处理器
                    .childHandler(new ServerInitializer());

            // 绑定端口并同步等待成功，即启动服务器
            ChannelFuture future = b.bind(8888);

            // 监听服务器端关闭，并阻塞等待
            future.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
