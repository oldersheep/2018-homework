package com.xxx.bio;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.Socket;

@Slf4j
public class ServerHandler implements Runnable {

    private Socket socket;

    public ServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)
        ){
            String expression;
            while (true) {
                if ((expression = reader.readLine()) == null) break;
                log.info("服务端收到消息{}", expression);

                int result = Calculator.cal(expression);

                writer.println(result); // 这里不用println会不继续执行？？？
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.error("发生错误：{}", e);
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                socket = null;
            }
        }
    }
}
