package com.xxx.design.proxy.staticed;

public class Service {

    private Dao dao;

    // 这里只能代理这一个DAO
    // 而且DAO一旦扩展，就需要在此处进行扩展
    public Service(Dao dao) {
        this.dao = dao;
    }

    public void modify() {
        System.out.println("开启事务===");
        dao.modify();
        System.out.println("提交事务，若有异常，则回滚！");
    }

    public void query() {
        System.out.println("可以自己玩，无需事务支持-_-");
        dao.query();
    }
}
