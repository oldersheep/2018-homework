package com.xxx.concurrent.chapter1;

/**
 * 测试类
 *
 * @author old boy
 * @version 1.0.0
 * @since 1.0.0
 */
public class Demo {

    PrintProcessor print;

    public Demo() {
        SaveProcessor save = new SaveProcessor();
        save.start();

        print = new PrintProcessor(save);
        print.start();
    }

    public static void main(String[] args) {
        Request request = new Request();
        request.setName("来了");

        new Demo().doRun(request);
    }

    private void doRun(Request request) {
        print.process(request);
    }
}
