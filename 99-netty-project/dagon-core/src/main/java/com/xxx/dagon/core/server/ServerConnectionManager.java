package com.xxx.dagon.core.server;

import com.xxx.dagon.api.connection.Connection;
import com.xxx.dagon.api.connection.ConnectionManager;
import com.xxx.dagon.network.connection.NettyConnection;
import com.xxx.dagon.tools.thread.NamedThreadFactory;
import com.xxx.dagon.tools.thread.ThreadNames;
import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.Timer;
import io.netty.util.TimerTask;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public final class ServerConnectionManager implements ConnectionManager {

    private boolean heartbeatCheck;
    private final ConnectionHolderFactory holderFactory;
    private final Map<ChannelId, ConnectionHolder> connections = new ConcurrentHashMap<>();
    private final ConnectionHolder DEFAULT_HOLDER = new SimpleConnectionHolder(null);
    private Timer timer;

    public ServerConnectionManager(boolean heartbeatCheck) {
        this.heartbeatCheck = heartbeatCheck;
        this.holderFactory = heartbeatCheck ? HeartBeatCheckTask::new : SimpleConnectionHolder::new;
    }

    @Override
    public void add(Connection connection) {
        connections.putIfAbsent(connection.getChannel().id(), holderFactory.create(connection));
    }

    @Override
    public Connection get(Channel channel) {

        return connections.getOrDefault(channel.id(), DEFAULT_HOLDER).get();
    }

    @Override
    public Connection removeAndClose(Channel channel) {
        ConnectionHolder holder = connections.remove(channel.id());
        if (Objects.nonNull(holder)) {
            Connection connection = holder.get();
            connection.close();
            return connection;
        }
        // 创建一个新连接，然后初始化、关闭
        Connection connection = new NettyConnection();
        connection.init(channel, false);
        connection.close();
        return null;
    }

    @Override
    public int getConnNum() {
        return connections.size();
    }

    @Override
    public void init() {
        if (heartbeatCheck) {
            long ticksDuration = TimeUnit.SECONDS.toMillis(1);// 1s走一步，心跳周期走一圈
            int ticksPerWheel = (int) (3 * 60 / ticksDuration); // TODO  配置类
            this.timer = new HashedWheelTimer(new NamedThreadFactory(ThreadNames.T_CONN_TIMER),
                    ticksDuration, TimeUnit.MILLISECONDS, ticksPerWheel);
        }
    }

    @Override
    public void destroy() {

    }

    private class HeartBeatCheckTask implements ConnectionHolder, TimerTask {

        private byte timeoutTimes = 0;
        private Connection connection;

        private HeartBeatCheckTask(byte timeoutTimes, Connection connection) {
            this.timeoutTimes = timeoutTimes;
            this.connection = connection;
        }

        public HeartBeatCheckTask(Connection connection) {
            this.connection = connection;
        }

        void startTimeout() {
            Connection connection = this.connection;

            if (Objects.nonNull(connection) && connection.isConnected()) {
                int timeout = connection.getSessionContext().heartbeat;
                // timer.newTimeout(this, timeout, TimeUnit.MILLISECONDS);
            }
        }

        @Override
        public Connection get() {
            return null;
        }

        @Override
        public void close() {

        }

        @Override
        public void run(Timeout timeout) throws Exception {
            Connection connection = this.connection;
            if (Objects.isNull(connection) || !connection.isConnected()) {
                // TODO add log
                return;
            }

            if (connection.isReadTimeout()) {
                // 超时次数大于配置的次数
                if (++timeoutTimes > 3) { // TODO 工具类
                    connection.close();
                    // TODO log
                    return;
                } else {
                    // TODO log
                }
            } else {
                timeoutTimes = 0;
            }
            startTimeout();
        }
    }

    @FunctionalInterface
    private interface ConnectionHolderFactory {

        ConnectionHolder create(Connection connection);
    }

    private interface ConnectionHolder {
        Connection get();

        void close();
    }

    private static class SimpleConnectionHolder implements ConnectionHolder {

        private final Connection connection;

        private SimpleConnectionHolder(Connection connection) {
            this.connection = connection;
        }

        @Override
        public Connection get() {
            return connection;
        }

        @Override
        public void close() {
            if (Objects.nonNull(connection)) {
                connection.close();
            }
        }
    }
}
