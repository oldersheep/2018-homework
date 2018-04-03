package com.xxx.factory.method;

import com.xxx.factory.Car;
import com.xxx.factory.Chevrolet;

public class ChevroletFactory implements CarFactory {

    @Override
    public Car getCar() {
        return new Chevrolet();
    }
}
