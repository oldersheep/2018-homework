package com.xxx.jvm;

/**
 * @Description -verbose:gc -XX:+PrintGCDetails
 * @Date 2019/7/1 21:17
 * @Version 1.0
 */
public class EdenAllocator {
    public static void main(String[] args) {
        // 优先分配在Eden区
        byte[] bytes = new byte[10 * 1024 * 1024];
    }
}
