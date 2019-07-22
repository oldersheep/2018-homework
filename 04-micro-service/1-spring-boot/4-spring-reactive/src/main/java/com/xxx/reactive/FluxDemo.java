package com.xxx.reactive;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

public class FluxDemo {

    public static void main(String[] args) {
        Integer block = Flux.just(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
                .filter(v -> v % 2 == 1) // 判断数值 获取奇数 Predicate
                .map(v -> v - 1) // 奇数变偶数
                .reduce(Integer::sum) // 聚合操作
                .subscribeOn(Schedulers.elastic())
                //.subscribeOn(Schedulers.parallel())
                .block()
        //        .subscribe(FluxDemo::printfln)
        ;
    }

    private static void printfln(Object message) {
        System.out.printf("[线程:%s]: %s\n", Thread.currentThread().getName(), message);
    }
}
