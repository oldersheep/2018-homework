package com.xxx.design.delegate.leader;

public class EmployeeA implements Employee {

    @Override
    public void doing(String command) {
        System.out.println("我是员工A，我负责干" + command + "工作。");
    }
}
