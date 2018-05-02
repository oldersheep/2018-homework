package com.xxx.design.factory.method;

import com.xxx.design.factory.Car;
import com.xxx.design.factory.Ferrari;

public class FerrariFactory implements CarFactory {
    @Override
    public Car getCar() {
        return new Ferrari();
    }
}
