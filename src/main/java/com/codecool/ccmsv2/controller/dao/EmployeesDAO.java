package com.codecool.ccmsv2.controller.dao;

import com.codecool.ccmsv2.model.Employee;

import java.util.List;

public interface EmployeesDAO {
    List<String> readEmployeesEmails();
    Employee readEmployeeByEmail(String email);
    List<Employee> readEmployees();
    void writeEmployees(List<Employee> employees);
}
