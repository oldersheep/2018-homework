package com.xxx.jvm;

/**
 * @Description -verbose:gc -XX:+PrintGCDetails -Xms10m -Xmx20m -Xmn10m -XX:PretenureSizeThreshold=6m
 * @Date 2019/7/1 21:22
 * @Version 1.0
 */
public class BigObjectIntoOldGen {
    public static void main(String[] args) {
        // 大对象直接进老年代
        byte[] bytes = new byte[6 * 1024 * 1024];

        // 长期存活的对象分配在老年代
        // -XX:MaxTenuringThreshold=15
    }
}
