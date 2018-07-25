package com.codecool.ccmsv2.controller.user;

import com.codecool.ccmsv2.controller.dao.*;
import com.codecool.ccmsv2.model.*;

import java.util.List;

public class ManagerController extends UserController {

    private StudentsDAO studentsDAO;
    private MentorsDAO mentorsDAO;
    private EmployeesDAO employeesDAO;

    public ManagerController(Manager manager) {
        super(manager);
        studentsDAO = new XMLStudentsDAO();
        mentorsDAO = new CsvMentorsDAO();
        employeesDAO = new CsvEmployeesDAO();
    }

    @Override
    public void startUserSession() {

        boolean isRunning = true;
        int option = 0;

        while (isRunning) {

            getView().printMenu("Log out",
                    "Show students",
                    "Show mentors",
                    "Add mentor",
                    "Edit mentor",
                    "Remove mentor",
                    "Show employees",
                    "Add employee",
                    "Edit employee",
                    "Remove employee");

            option = getView().getInputInt(0, 9);

            switch (option) {

                case 0:
                    isRunning = false;
                    break;
                case 1:
                    showStudents();
                    break;
                case 2:
                    showMentors();
                    break;
                case 3:
                    addMentor();
                    break;
                case 4:
                    editMentor();
                    break;
                case 5:
                    removeMentor();
                    break;
                case 6:
                    showEmployees();
                    break;
                case 7:
                    addEmployee();
                    break;
                case 8:
                    editEmployee();
                    break;
                case 9:
                    removeEmployee();
                    break;
            }
        }

    }

    private void showUsers(List<? extends User> users) {

        for (int i=0; i<users.size(); i++) {
            getView().print(String.format("\n%d. %s", i + 1, users.get(i)));
        }
        getView().print("\n");

    }

    private void showStudents() {
        List<Student> students = studentsDAO.readStudents();
        showUsers(students);
        getView().waitForConfirm();
    }

    private void showMentors() {
        List<Mentor> mentors = mentorsDAO.readMentors();
        showUsers(mentors);
        getView().waitForConfirm();
    }

    private String[] getBasicUserData() {

        String name = getView().getInputString("Enter name: ");
        String email = getEmail("Enter email: ");
        String password = getView().getInputString("Enter password: ");

        return new String[]{name, email, password};
    }

    private String getEmail(String message) {

        String email = "";

        while (email.equals("")) {

            email = getView().getInputString(message);
            if (!isEmailUnique(email)) {
                getView().print("That email already exists in database. Use another.");
                email = "";
            }
        }

        return email;
    }

    private void addMentor() {

        List<Mentor> mentors = mentorsDAO.readMentors();
        String[] basicUserData = getBasicUserData();
        Mentor mentor = new Mentor(basicUserData[0], basicUserData[1], basicUserData[2]);
        mentors.add(mentor);
        mentorsDAO.writeMentors(mentors);
    }

    private void editMentor() {

        List<Mentor> mentors = mentorsDAO.readMentors();
        Mentor mentor = mentors.get(chooseUser(mentors));
        int chosenDataIndex = chooseUserData();

        switch (chosenDataIndex) {
            case 1:
                getView().print("Old name: " + mentor.getName());
                String name = getView().getInputString("New name: ");
                mentor.setName(name);
                break;
            case 2:
                getView().print("Old email: " + mentor.getEmail());
                String email = getEmail("New email: ");
                mentor.setEmail(email);
                break;
            case 3:
                getView().print("Old password: " + mentor.getPassword());
                String password = getView().getInputString("New password: ");
                mentor.setPassword(password);
                break;
        }

        mentorsDAO.writeMentors(mentors);

    }

    private int chooseUser(List<? extends User> users) {
        showUsers(users);
        return getView().getInputInt(1, users.size()) - 1;
    }

    private int chooseUserData() {
        String[] data = new String[]{"name", "email", "password"};

        getView().print("\nChoose data to edit:");
        for (int i=0; i<data.length; i++){
            getView().print(String.format("\n%d. %s", i + 1, data[i]));
        }

        return getView().getInputInt(1, data.length);
    }

    private void removeMentor() {
        List<Mentor> mentors = mentorsDAO.readMentors();
        getView().print("\nChoose a mentor you want to remove:");
        mentors.remove(chooseUser(mentors));
        mentorsDAO.writeMentors(mentors);
    }

    private void showEmployees() {
        List<Employee> employees = employeesDAO.readEmployees();
        showUsers(employees);
        getView().waitForConfirm();
    }

    private void addEmployee() {

        List<Employee> employees = employeesDAO.readEmployees();
        String[] basicUserData = getBasicUserData();
        Employee employee = new Employee(basicUserData[0], basicUserData[1], basicUserData[2]);
        employees.add(employee);
        employeesDAO.writeEmployees(employees);
    }

    private void editEmployee() {

        List<Employee> employees = employeesDAO.readEmployees();
        Employee employee = employees.get(chooseUser(employees));
        int chosenDataIndex = chooseUserData();

        switch (chosenDataIndex) {
            case 1:
                getView().print("Old name: " + employee.getName());
                String name = getView().getInputString("New name: ");
                employee.setName(name);
                break;
            case 2:
                getView().print("Old email: " + employee.getEmail());
                String email = getEmail("New email: ");
                employee.setEmail(email);
                break;
            case 3:
                getView().print("Old password: " + employee.getPassword());
                String password = getView().getInputString("New password: ");
                employee.setPassword(password);
                break;
        }

        employeesDAO.writeEmployees(employees);
    }

    private void removeEmployee() {
        List<Employee> employees = employeesDAO.readEmployees();
        getView().print("\nChoose a employee you want to remove:");
        employees.remove(chooseUser(employees));
        employeesDAO.writeEmployees(employees);
    }
}
