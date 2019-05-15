package com.xxx.zookeeper.imooc;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.data.Stat;

public class GetCallBack implements AsyncCallback.DataCallback {

    @Override
    public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
        System.out.println("获取节点: " + path);
        System.out.println((String)ctx);
    }
}
