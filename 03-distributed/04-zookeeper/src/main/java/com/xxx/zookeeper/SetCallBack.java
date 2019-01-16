package com.xxx.zookeeper;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.data.Stat;

public class SetCallBack implements AsyncCallback.StatCallback {

    @Override
    public void processResult(int rc, String path, Object ctx, Stat stat) {
        System.out.println("更改节点: " + path);
        System.out.println((String)ctx);
    }
}
