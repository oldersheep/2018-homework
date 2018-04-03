package com.xxx.factory.abstr;

import com.xxx.factory.Chevrolet;
import com.xxx.factory.Ferrari;
import com.xxx.factory.Maserati;

public abstract class AbstactFactory {

    abstract Chevrolet getChevrolet();

    abstract Ferrari getFerrari();

    abstract Maserati getMaserati();
}
