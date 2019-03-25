package com.xxx.reactive;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureDemo {

    public static void main(String[] args) {

        printfln("hello world");
        CompletableFuture.supplyAsync(()->{
            printfln("第一步，返回一个\"hello\"！");
            return "hello";
        }).thenApplyAsync(result -> { // 是异步么？？？
            printfln("第二步，在第一步的结果上加上\" word\"进行返回！");
            return result + " word";
        }).thenAccept(CompletableFutureDemo::printfln)
        .whenComplete((v, error) -> {
            System.out.println(v);
            System.out.println("执行结束!");
        })
        .join()
        ;
    }

    private static void printfln(String message) {
        System.out.printf("[线程:%s] event: %s\n", Thread.currentThread().getName(), message);
    }
}
