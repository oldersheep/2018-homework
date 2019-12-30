package com.xxx.concurrent.chapter1;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * 保存的请求处理器
 *
 * @author old boy
 * @version 1.0.0
 * @since 1.0.0
 */
public class SaveProcessor extends Thread implements RequestProcessor {

    private LinkedBlockingQueue<Request> queue = new LinkedBlockingQueue<>();

    @Override
    public void run() {
        while (true) {
            try {
                Request request = queue.take();
                System.out.println("save ->" + request);
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
