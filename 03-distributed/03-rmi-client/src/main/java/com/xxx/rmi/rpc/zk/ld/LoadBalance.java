package com.xxx.rmi.rpc.zk.ld;

import java.util.List;

/**
 * @Description
 * @Date 2019/5/21 21:40
 * @Version 1.0
 */
public interface LoadBalance {

    String selectHost(List<String> repositories);
}
