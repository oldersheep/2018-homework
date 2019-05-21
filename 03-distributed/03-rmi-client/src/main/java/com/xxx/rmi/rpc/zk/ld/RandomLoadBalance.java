package com.xxx.rmi.rpc.zk.ld;

import java.util.List;
import java.util.Random;

/**
 * @Description
 * @Date 2019/5/21 21:44
 * @Version 1.0
 */
public class RandomLoadBalance extends AbstractLoadBalance {

    @Override
    String doSelect(List<String> repositories) {
        int size = repositories.size();
        Random random = new Random();
        return repositories.get(random.nextInt(size));
    }
}
