package com.xxx.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

public class NettyServer {

    private static final String IP_ADDRESS = "127.0.0.1";
    private static final int PORT = 7777;
    private static final int BIZ_GROUP_SIZE = Runtime.getRuntime().availableProcessors();
    private static final int BIZ_THREAD_SIZE = 100;

    private static final EventLoopGroup BOSS_GROUP = new NioEventLoopGroup(BIZ_GROUP_SIZE);
    private static final EventLoopGroup WORK_GROUP = new NioEventLoopGroup(BIZ_THREAD_SIZE);

    public static void start() throws InterruptedException {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(BOSS_GROUP, WORK_GROUP)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel channel) throws Exception {
                        ChannelPipeline pipeline = channel.pipeline();
                        pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
                        pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
                        pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));

                    }
                });
        ChannelFuture channelFuture = serverBootstrap.bind(IP_ADDRESS, PORT).sync();
        channelFuture.channel().closeFuture().sync();
        System.out.println("server start");
    }

    protected void stop() {
        WORK_GROUP.shutdownGracefully();
        BOSS_GROUP.shutdownGracefully();
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("main start");
        start();
    }

}
