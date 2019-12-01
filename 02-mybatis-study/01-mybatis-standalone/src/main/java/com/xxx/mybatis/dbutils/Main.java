package com.xxx.mybatis.dbutils;

import com.xxx.mybatis.dbutils.dao.BlogDao;

public class Main {
    public static void main(String[] args) throws Exception {
        HikariUtil.init();
        BlogDao.selectBlog(1);
        BlogDao.selectList();
    }
}
