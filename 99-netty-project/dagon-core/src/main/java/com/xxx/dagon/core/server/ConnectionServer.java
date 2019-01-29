package com.xxx.dagon.core.server;

import com.xxx.dagon.api.connection.ConnectionManager;
import com.xxx.dagon.core.DagonServer;
import com.xxx.dagon.network.server.NettyTcpServer;

public final class ConnectionServer extends NettyTcpServer {

    private DagonServer dagonServer;
    private ConnectionManager connectionManager;

    public ConnectionServer(DagonServer dagonServer) {
        super(1, "");
        this.dagonServer = dagonServer;
        this.connectionManager = new ServerConnectionManager(true);
    }
}
