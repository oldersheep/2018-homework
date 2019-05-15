package com.xxx.zookeeper.imooc.countdown;

import java.util.concurrent.CountDownLatch;

public class StationBeijingShaHe extends DangerCenter {

	public StationBeijingShaHe(CountDownLatch countDown) {
		super(countDown, "北京沙河调度站");
	}

	@Override
	public void check() {
		System.out.println("正在检查 [" + this.getStation() + "]...");
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("检查 [" + this.getStation() + "] 完毕，可以发车~");
	}

}
