package com.mytest.crud.service;

import com.mytest.crud.bean.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAll();

    void saveEmp(Employee employee);

    boolean checkUser(String userName);

    Employee getEmp(Integer id);

    void updataEmp(Employee employee);

    void deleteEmp(Integer empId);

    void deleteBatch(List<Integer> str_ids);
}
