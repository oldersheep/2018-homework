package com.xxx.jvm;

/**
 * @Description -Xmx1024m -Xms1024m -XX:+PrintGCDetails
 * @Date 2019/6/26 21:53
 * @Version 1.0
 */
public class MemoryCalculator {

    public static void main(String[] args) {
        long maxMemory = Runtime.getRuntime().maxMemory();
        long totalMemory = Runtime.getRuntime().totalMemory();

        System.out.println("Max Memory = " + maxMemory / 1024d / 1024d + "M");
        System.out.println("Total Memory = " + totalMemory / 1024d / 1024d + "M");
    }
}
