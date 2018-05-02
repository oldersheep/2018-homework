package com.xxx.design.factory.method;

import com.xxx.design.factory.Car;

/**
 * 工厂方法模式：提供一个造汽车的接口，制定流程，具体实现，由专门工厂负责。
 * 这就有一种规范的流程管理了。
 */
public interface CarFactory {

    Car getCar();

}
