package com.xxx.distributed.protocol.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @ClassName ServerDemo
 * @Description
 * @Author aitaiyo
 * @Date 2019/4/25 21:48
 * @Version 1.0
 */
public class ServerDemo {

    private static final int PORT = 8080;
    private static byte[] data = new byte[1024];
    public static void main(String[] args) {
        try (DatagramSocket datagramSocket = new DatagramSocket(PORT)) {
            DatagramPacket datagramPacket = new DatagramPacket(data, data.length);
            datagramSocket.receive(datagramPacket);

            System.out.println(new String(data, 0, datagramPacket.getLength()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
