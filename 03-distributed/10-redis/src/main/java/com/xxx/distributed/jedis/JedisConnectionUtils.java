package com.xxx.distributed.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @Description
 * @Date 2019/7/10 21:43
 * @Version 1.0
 */
public class JedisConnectionUtils {

    final static String HOST = "localhost";
    final static Integer PORT = 6379;
    static JedisPool pool = new JedisPool(HOST, PORT);

    public static Jedis getJedis() {

        return pool.getResource();
    }
}
