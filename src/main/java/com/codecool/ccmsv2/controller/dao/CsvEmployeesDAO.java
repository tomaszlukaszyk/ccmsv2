package com.codecool.ccmsv2.controller.dao;

import com.codecool.ccmsv2.model.Employee;

import java.util.List;

public class CsvEmployeesDAO implements EmployeesDAO {

    final String filePath = CsvEmployeesDAO.class.getClassLoader().getResource("users/employees.csv").getPath();
    final int EMAIL_COL = 0;
    final int NAME_COL = 1;
    final int PASSWORD_COL = 2;

    @Override
    public List<String> readEmployeesEmails() {
        return null;
    }

    @Override
    public Employee readEmployeeByEmail(String email) {
        return null;
    }

    @Override
    public List<Employee> readEmployees() {
        return null;
    }

    @Override
    public void writeEmployees(List<Employee> employees) {

    }
}
