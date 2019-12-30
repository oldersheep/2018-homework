package com.xxx.concurrent;

import java.util.concurrent.*;

/**
 * Callable 的实现
 *
 * @author old boy
 * @version 1.0.0
 * @since 1.0.0
 */
public class CallableDemo implements Callable<String> {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CallableDemo demo = new CallableDemo();

        Future<String> future = executorService.submit(demo);

        System.out.println(future.get()); // 这里会阻塞

        executorService.shutdown();
    }

    @Override
    public String call() throws Exception {
        return "End..";
    }
}
