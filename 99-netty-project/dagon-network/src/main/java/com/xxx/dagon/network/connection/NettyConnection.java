package com.xxx.dagon.network.connection;

import com.xxx.dagon.api.connection.Connection;
import com.xxx.dagon.api.connection.SessionContext;
import com.xxx.dagon.api.protocol.Packet;
import com.xxx.dagon.api.spi.core.RsaCipherFactory;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

// 连接器，异步
public class NettyConnection implements Connection, ChannelFutureListener {

    private static final Logger log = LoggerFactory.getLogger(NettyConnection.class);
    private Channel channel;
    private SessionContext context;
    private Long lastReadTime;
    private Long lastWriteTime;
    private volatile byte status = STATUS_NEW;

    @Override
    public void init(Channel channel, boolean security) {
        this.channel = channel;
        this.context = new SessionContext();
        this.lastReadTime = System.currentTimeMillis();
        this.status = STATUS_CONNECTED;
        if (security) {
            // 搞个签名
            this.context.changeCipher(RsaCipherFactory.create());
        }
    }

    @Override
    public SessionContext getSessionContext() {
        return this.context;
    }

    @Override
    public void setSessionContext(SessionContext context) {
        this.context = context;
    }

    @Override
    public ChannelFuture send(Packet packet) {
        return this.send(packet, null);
    }

    @Override
    public ChannelFuture send(Packet packet, ChannelFutureListener listener) {

        if (channel.isActive()) {
            ChannelFuture future = channel.writeAndFlush(packet);
            if (Objects.nonNull(listener)) {
                future.addListener(listener);
            }

            if (channel.isWritable()) {
                return future;
            }

            if (!future.channel().eventLoop().inEventLoop()) {
                future.awaitUninterruptibly(200); // 200是一个经验值，不要超过500
            }

            return future;
        } else {
            // 连接没有激活，直接关闭
            return this.close();
        }
    }

    @Override
    public String getId() {

        return this.channel.id().asShortText();
    }

    @Override
    public ChannelFuture close() {
        if (status == STATUS_DISCONNECTED) {
            return null;
        }
        this.status = STATUS_DISCONNECTED;
        return this.channel.close();
    }

    @Override
    public boolean isConnected() {
        return status == STATUS_CONNECTED;
    }

    @Override
    public boolean isReadTimeout() {

        return System.currentTimeMillis() - lastReadTime > context.heartbeat + 1000;
    }

    @Override
    public boolean isWriteTimeout() {

        return System.currentTimeMillis() - lastWriteTime > context.heartbeat - 1000;
    }

    @Override
    public void updateLastReadTime() {
        lastReadTime = System.currentTimeMillis();
    }

    @Override
    public void updateLastWriteTime() {
        lastWriteTime = System.currentTimeMillis();
    }

    @Override
    public Channel getChannel() {
        return this.channel;
    }

    @Override
    public void operationComplete(ChannelFuture channelFuture) throws Exception {
        if (channelFuture.isSuccess()) {
            lastWriteTime = System.currentTimeMillis();
        } else {
            log.error("connection send msg error", channelFuture.cause());
        }
    }

    public Long getLastReadTime() {
        return lastReadTime;
    }

    public void setLastReadTime(Long lastReadTime) {
        this.lastReadTime = lastReadTime;
    }

    public Long getLastWriteTime() {
        return lastWriteTime;
    }

    public void setLastWriteTime(Long lastWriteTime) {
        this.lastWriteTime = lastWriteTime;
    }
}
