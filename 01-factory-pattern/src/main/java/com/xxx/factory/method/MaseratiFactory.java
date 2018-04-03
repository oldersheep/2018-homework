package com.xxx.factory.method;

import com.xxx.factory.Car;
import com.xxx.factory.Maserati;

public class MaseratiFactory implements CarFactory {
    @Override
    public Car getCar() {
        return new Maserati();
    }
}
