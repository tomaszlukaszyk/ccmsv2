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

        updateChosenData(chosenDataIndex, mentor);

        mentorsDAO.writeMentors(mentors);

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

        updateChosenData(chosenDataIndex, employee);

        employeesDAO.writeEmployees(employees);
    }

    private void removeEmployee() {
        List<Employee> employees = employeesDAO.readEmployees();
        getView().print("\nChoose a employee you want to remove:");
        employees.remove(chooseUser(employees));
        employeesDAO.writeEmployees(employees);
    }
}
