package com.xxx.design.template;

import com.xxx.design.template.dao.MemberDao;

import java.util.List;

public class JdbcTemplateTest {

    public static void main(String[] args) {
        MemberDao memberDao = new MemberDao();
        List<?> list = memberDao.query();
        for (Object o : list) {
            System.out.println(o);
        }
    }
}
