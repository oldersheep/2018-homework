package com.xxx.design.factory.abstr;

import com.xxx.design.factory.Chevrolet;
import com.xxx.design.factory.Ferrari;
import com.xxx.design.factory.Maybach;

/**
 * 抽象工厂：提供一个抽象类，目前搭好了这么几个工厂，由工厂里面的各个部门负责管理。
 */
public abstract class AbstactFactory {

    abstract Chevrolet getChevrolet();

    abstract Ferrari getFerrari();

    abstract Maybach getMaybach();
}
