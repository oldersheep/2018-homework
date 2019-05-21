package com.xxx.rmi.rpc.zk.ld;

import java.util.List;

/**
 * @Description
 * @Date 2019/5/21 21:41
 * @Version 1.0
 */
public abstract class AbstractLoadBalance implements LoadBalance {

    @Override
    public String selectHost(List<String> repositories) {
        if (repositories == null || repositories.isEmpty()) {
            return null;
        }
        if (repositories.size() == 1) {
            return repositories.get(0);
        }
        return doSelect(repositories);
    }

    abstract String doSelect(List<String> repositories);
}
