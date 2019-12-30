package com.xxx.concurrent.chapter1;

/**
 * 请求处理器
 *
 * @author old boy
 * @version 1.0.0
 * @since 1.0.0
 */
public interface RequestProcessor {

    /**
     * 处理请求
     * @param request 数据封装
     */
    void process(Request request);
}
