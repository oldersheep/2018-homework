package com.xxx.reactive.webflux;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class WebFluxController {

    @RequestMapping
    public Mono<String> index() {
        printfln("开始执行");

        Mono<String> result = Mono.fromSupplier(() -> {
            printfln("开始计算");
            return "hello world";
        });
        return result;
    }

    private static void printfln(Object message) {
        System.out.printf("[线程:%s]: %s\n", Thread.currentThread().getName(), message);
    }
}
