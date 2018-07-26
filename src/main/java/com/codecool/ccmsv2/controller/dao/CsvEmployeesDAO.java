package com.codecool.ccmsv2.controller.dao;

import com.codecool.ccmsv2.model.Employee;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Scanner;

public class CsvEmployeesDAO implements EmployeesDAO {

    private final String filePath = CsvEmployeesDAO.class.getClassLoader().getResource("users/employees.csv").getPath();
    private final int EMAIL_COL = 0;
    private final int NAME_COL = 1;
    private final int PASSWORD_COL = 2;

    @Override
    public List<String> readEmployeesEmails() {

        List<String> emails = new ArrayList<>();
        Scanner fileReader = getScanner();

        while (fileReader.hasNext()) {
            String[] line = fileReader.nextLine().split("\\|");
            emails.add(line[EMAIL_COL]);
        }

        return emails;
    }

    @Override
    public Employee readEmployeeByEmail(String email) {

        Scanner fileReader = getScanner();

        while (fileReader.hasNext()) {
            String[] line = fileReader.nextLine().split("\\|");

            if (line[EMAIL_COL].equals(email))
                return new Employee(line[NAME_COL], email, line[PASSWORD_COL]);
        }

        return null;
    }

    @Override
    public List<Employee> readEmployees() {

        List<Employee> employees = new ArrayList<>();
        Scanner fileReader = getScanner();

        while (fileReader.hasNext()) {

            String[] line = fileReader.nextLine().split("\\|");
            employees.add(new Employee(line[NAME_COL], line[EMAIL_COL], line[PASSWORD_COL]));

        }

        return employees;
    }

    @Override
    public void writeEmployees(List<Employee> employees) {

        try (Formatter writer = new Formatter(filePath)) {

            for (Employee employee: employees) {
                writer.format("%s|%s|%s\n", employee.getEmail(), employee.getName(), employee.getPassword());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Scanner getScanner() {

        Scanner fileReader = null;

        try {
            fileReader = new Scanner(new File(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return fileReader;
    }
}
