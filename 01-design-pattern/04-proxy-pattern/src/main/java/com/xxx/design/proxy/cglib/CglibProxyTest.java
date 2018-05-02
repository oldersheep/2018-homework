package com.xxx.design.proxy.cglib;

public class CglibProxyTest {

    public static void main(String[] args) {
        Dao dao = (Dao) new Service().getInstance(Dao.class);

        System.out.println(dao.getClass());
        dao.modify();
        dao.query();
    }
}
