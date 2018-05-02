package com.xxx.design.factory.method;

import com.xxx.design.factory.Car;
import com.xxx.design.factory.Maybach;

public class MaybachFactory implements CarFactory {
    @Override
    public Car getCar() {
        return new Maybach();
    }
}
