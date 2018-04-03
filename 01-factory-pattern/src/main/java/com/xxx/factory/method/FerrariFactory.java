package com.xxx.factory.method;

import com.xxx.factory.Car;
import com.xxx.factory.Ferrari;

public class FerrariFactory implements CarFactory {

    @Override
    public Car getCar() {
        return new Ferrari();
    }
}
