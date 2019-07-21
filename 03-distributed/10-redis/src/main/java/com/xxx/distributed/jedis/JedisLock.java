package com.xxx.distributed.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.UUID;

/**
 * @Description
 * @Date 2019/7/10 21:31
 * @Version 1.0
 */
public class JedisLock {

    private static final String LOCK_PREFIX = "lock:";

    public String acquiredLock(String lockName, long acquiredTimeout, long lockTimeout) {
        // 保证释放锁的时候是同一个持有锁的人
        String identifier = UUID.randomUUID().toString();
        String lockKey = LOCK_PREFIX + lockName;
        int lockExpire = (int) (lockTimeout / 1000);
        Jedis jedis = null;

        try {
            jedis = JedisConnectionUtils.getJedis();

            long end = System.currentTimeMillis() + acquiredTimeout;
            // 获取锁的限定时间
            while (System.currentTimeMillis() < end) {
                if (jedis.setnx(lockKey, identifier) == 1) { // 设置值成功
                    jedis.expire(lockKey, lockExpire);       // 设置超时时间
                    return identifier;                      // 获得锁成功
                }
                if (jedis.ttl(lockKey) == -1) {
                    jedis.expire(lockKey, lockExpire);
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            jedis.close();
        }
        return null;
    }

    public boolean releaseLockWithLua(String lockName, String identifier) {
        String lockKey = LOCK_PREFIX + lockName;
        Jedis jedis = null;
        try {
            jedis = JedisConnectionUtils.getJedis();
            String lua = "if redis.call(\"get\", KEYS[1] == ARGV[1]) " +
                    "then return redis.call(\"del\", KEYS[1]) " +
                    "else return 0 end";
            Object result = jedis.eval(lua, 1, new String[]{lockKey, identifier});
            if (Integer.valueOf(result + "") > 0) {
                return true;
            }
            return false;
        } finally {
            if (jedis != null)
                jedis.close();
        }
    }

    public boolean releaseLock(String lockName, String identifier) {
        String lockKey = LOCK_PREFIX + lockName;
        Jedis jedis = null;
        boolean release = false;
        try {
            jedis = JedisConnectionUtils.getJedis();
            while (true) {
                jedis.watch(lockKey);
                if (identifier.equals(jedis.get(lockKey))) {
                    Transaction tx = jedis.multi();
                    tx.del(lockKey);
                    if (tx.exec().isEmpty()) {
                        continue;
                    }
                    release = true;
                }
                // TODO 如果不等呢？
                jedis.unwatch();
                break;
            }
        } finally {
            if (jedis != null)
                jedis.close();
        }
        return release;
    }
}
