package com.xxx.mybatis.service;

import com.xxx.mybatis.bean.Department;
import com.xxx.mybatis.dao.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;

    public List<Department> getDepts() {

        List<Department> list = departmentMapper.selectByMap(null);
        return list;
    }
}
