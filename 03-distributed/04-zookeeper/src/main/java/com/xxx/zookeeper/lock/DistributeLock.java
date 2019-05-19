package com.xxx.zookeeper.lock;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @Description 分布式锁
 * @Date 2019/5/19 16:27
 * @Version 1.0
 */
public class DistributeLock implements Lock, Watcher {

    private static final String connectString = "192.168.0.111:2181,192.168.0.112:2181,192.168.0.113:2181";
    final static Logger log = LoggerFactory.getLogger(DistributeLock.class);
    private ZooKeeper zk = null;
    private String ROOT_LOCK = "/locks"; // 定义根节点
    private String WAIT_LOCK; // 等待前一个锁
    private String CURRENT_LOCK; // 表示当前锁

    private CountDownLatch countDownLatch;

    public DistributeLock() {
        try {
            zk = new ZooKeeper(connectString, 4000, this);
            Stat stat = zk.exists(ROOT_LOCK, false);
            if (stat == null) {
                zk.create(ROOT_LOCK, "0".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (IOException | InterruptedException | KeeperException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean tryLock() {
        // 创建临时有序节点
        try {
            CURRENT_LOCK = zk.create(ROOT_LOCK + "/", "0".getBytes(),
                    ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            log.warn("{}->{}尝试竞争锁", Thread.currentThread().getName(), CURRENT_LOCK);

            // 获取根节点下所有的子节点
            List<String> childrenList = zk.getChildren(ROOT_LOCK, false);
            TreeSet<String> pathSet = new TreeSet<>();
            for (String children : childrenList) {
                pathSet.add(ROOT_LOCK + "/" + children);
            }
            String firstNode = pathSet.first();
            SortedSet<String> lessThanMe = pathSet.headSet(CURRENT_LOCK);

            // 如果当前锁与最小的锁相等，表示获得锁成功
            if (CURRENT_LOCK.equals(firstNode)) {
                return true;
            }

            if (!lessThanMe.isEmpty()) {
                // 获得比当前节点更小的最后一个节点，设置给WAIT_LOCK
                WAIT_LOCK = lessThanMe.last();
            }
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void lock() {
        if (this.tryLock()) {
            log.warn("{}->{}获得锁成功", Thread.currentThread().getName(), CURRENT_LOCK);
            return;
        }
        try {
            waitForLock(WAIT_LOCK);
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void waitForLock(String prev) throws KeeperException, InterruptedException {
        // 监听当前节点的上一个节点
        Stat stat = zk.exists(prev, true);
        if (stat != null) {
            log.warn("{}->等待锁{}释放", Thread.currentThread().getName(), ROOT_LOCK + "/" + prev);
            countDownLatch = new CountDownLatch(1);
            countDownLatch.await();
            log.warn("{}->获得锁成功", Thread.currentThread().getName());
        }
    }

    @Override
    public void lockInterruptibly() {

    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) {

        return false;
    }

    @Override
    public void unlock() {
        log.warn("{}->释放锁{}", Thread.currentThread().getName(), CURRENT_LOCK);
        try {
            zk.delete(CURRENT_LOCK, -1);
            CURRENT_LOCK = null;
            zk.close();
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    @Override
    public void process(WatchedEvent event) {
        if (this.countDownLatch != null) {
            countDownLatch.countDown();
        }
    }
}
