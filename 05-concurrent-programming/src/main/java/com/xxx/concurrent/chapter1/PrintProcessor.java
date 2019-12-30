package com.xxx.concurrent.chapter1;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * 打印的请求处理器
 *
 * @author old boy
 * @version 1.0.0
 * @since 1.0.0
 */
public class PrintProcessor extends Thread implements RequestProcessor {

    private LinkedBlockingQueue<Request> queue = new LinkedBlockingQueue<>();
    private final RequestProcessor nextProcessor;

    public PrintProcessor(RequestProcessor nextProcessor) {

        this.nextProcessor = nextProcessor;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Request request = queue.take();
                nextProcessor.process(request);
                Thread.sleep(2000);
                System.out.println("print ->" + request);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void process(Request request) {
        queue.add(request);
    }
}
