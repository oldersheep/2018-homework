package com.xxx.distributed;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisSentinelPool;


/**
 * @Description
 * @Date 2019/7/10 20:36
 * @Version 1.0
 */
public class Test {

    public static void main(String[] args) {
//        new JedisSentinelPool();
//        JedisCluster cluster = new JedisCluster();

        Config config = new Config();
        config.useClusterServers().addNodeAddress("", "");

        RedissonClient redissonClient = Redisson.create(config);
        redissonClient.getLock("");
        redissonClient.getBucket("key").set("value");
        redissonClient.getSet("").addAsync("");
        redissonClient.getList("").set(1, "");
    }
}
