package com.xxx.dagon.network.server;

import com.xxx.dagon.api.service.BaseService;
import com.xxx.dagon.api.service.Server;
import com.xxx.dagon.api.service.ServiceException;
import io.netty.channel.EventLoopGroup;

import java.util.concurrent.atomic.AtomicReference;

public abstract class NettyTcpServer extends BaseService implements Server {

    protected final int port;
    protected final String host;
    protected final AtomicReference<State> serverState = new AtomicReference<>(State.CREATED);
    public enum State {
        CREATED, INITIALIZED, STARING, STARTED, SHUTDOWN;
    }

    protected EventLoopGroup bossGroup;
    protected EventLoopGroup workGroup;

    public NettyTcpServer(int port) {
        this.port = port;
        this.host = null;
    }

    public NettyTcpServer(int port, String host) {
        this.port = port;
        this.host = host;
    }

    public void init() {
        if (!serverState.compareAndSet(State.CREATED, State.INITIALIZED)) {
            throw new ServiceException("Server already init...");
        }
    }
}
