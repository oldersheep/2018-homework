package com.xxx.design.delegate.leader;

public class EmployeeB implements Employee {

    @Override
    public void doing(String command) {
        System.out.println("我是员工B，我负责干" + command + "工作。");
    }
}
