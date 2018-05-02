package com.xxx.design.proxy.custom;

public class CustomJdkProxyTest {

    public static void main(String[] args) {
        Dao dao = (Dao) new Service().getInstance(new DaoImpl());

        dao.modify();

        dao.query();
    }
}
