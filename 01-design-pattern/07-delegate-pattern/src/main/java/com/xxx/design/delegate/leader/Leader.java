package com.xxx.design.delegate.leader;

import java.util.HashMap;
import java.util.Map;

public class Leader  implements Employee{

    private Map<String, Employee> employees = new HashMap<>();

    public Leader() {
        employees.put("加密", new EmployeeA());
        employees.put("登录", new EmployeeB());
    }

    public void doing(String command) {

        employees.get(command).doing(command);
    }

}
