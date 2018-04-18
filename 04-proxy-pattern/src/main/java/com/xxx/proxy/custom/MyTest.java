package com.xxx.proxy.custom;

public class MyTest {

    public static void main(String[] args) {
        Dao dao = (Dao) new MyService().getInstance(new DaoImpl());

        dao.modify();

        dao.query();
    }
}
