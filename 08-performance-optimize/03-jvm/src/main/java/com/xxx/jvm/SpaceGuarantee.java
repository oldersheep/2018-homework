package com.xxx.jvm;

/**
 * @Description 空间担保 -verbose:gc -XX:+PrintGCDetails -Xms10m -Xmx20m -Xmn10m -XX:PretenureSizeThreshold=6m
 * @Date 2019/7/1 21:29
 * @Version 1.0
 */
public class SpaceGuarantee {
    public static void main(String[] args) {
        byte[] b1 = new byte[2 * 1024 * 1024];
        byte[] b2 = new byte[2 * 1024 * 1024];
        byte[] b3 = new byte[2 * 1024 * 1024];
        byte[] b4 = new byte[4 * 1024 * 1024];

        System.gc();
    }
}
