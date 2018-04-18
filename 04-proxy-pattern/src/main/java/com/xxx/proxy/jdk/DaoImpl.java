package com.xxx.proxy.jdk;

public class DaoImpl implements Dao {


    @Override
    public void modify() {
        System.out.println("增删改操作...");
    }

    @Override
    public void query() {
        System.out.println("查询，各种查。");
    }
}
