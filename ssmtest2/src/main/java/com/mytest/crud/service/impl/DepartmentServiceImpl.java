package com.mytest.crud.service.impl;

import com.mytest.crud.bean.Department;
import com.mytest.crud.dao.DepartmentMapper;
import com.mytest.crud.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    DepartmentMapper departmentMapper;

     public List<Department> getDepts(){
         List<Department> list = departmentMapper.selectByExample(null);
         return list;
    }
}
