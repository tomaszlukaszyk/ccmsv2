package com.codecool.ccmsv2.controller;

import com.codecool.ccmsv2.controller.dao.*;
import com.codecool.ccmsv2.controller.user.*;
import com.codecool.ccmsv2.model.*;
import com.codecool.ccmsv2.view.View;

import java.util.List;

public class Logger {

    private UserController userController;
    private View view = new View();
    private StudentsDAO studentsDAO = new XMLStudentsDAO();
    private MentorsDAO mentorsDAO = new CsvMentorsDAO();
    private EmployeesDAO employeesDAO = new CsvEmployeesDAO();
    private ManagersDAO managersDAO = new CsvManagersDAO();
    private boolean userInDatabase = false;

    public void logIn() {

        userController = null;
        String email = view.getInputString("Enter email: ");
        setControllerIfEmailOnStudentsList(email);
        setControllerIfEmailOnMentorsList(email);
        setControllerIfEmailOnEmployeesList(email);
        setControllerIfEmailOnManagersList(email);

        if (!userInDatabase && userController == null) {
            view.print("User with that email dose not exist");
        }
        else if (userController != null) {
            userController.welcomeUser();
            userController.startUserSession();
        }

    }

    private boolean passwordCorrect(String password) {
        String enteredPassword = view.getInputString("Enter password: ");
        return enteredPassword.equals(password);
    }

    private void setControllerIfEmailOnStudentsList(String email){
        List<String> studentsEmails = studentsDAO.readStudentsEmails();

        if (studentsEmails.contains(email)) {
            Student student = studentsDAO.readStudentByEmail(email);
            userInDatabase = true;
            if (passwordCorrect(student.getPassword()))
                userController = new StudentController(student);
            else
                view.print("Incorrect password");
        }
    }

    private void setControllerIfEmailOnMentorsList(String email){
        List<String> mentorEmails = mentorsDAO.readMentorsEmails();

        if (mentorEmails.contains(email)) {
            Mentor mentor = mentorsDAO.readMentorByEmail(email);
            userInDatabase = true;
            if (passwordCorrect(mentor.getPassword()))
                userController = new MentorController(mentor);
            else
                view.print("Incorrect password");
        }
    }

    private void setControllerIfEmailOnEmployeesList(String email){
        List<String> employeesEmails = employeesDAO.readEmployeesEmails();

        if (employeesEmails.contains(email)) {
            Employee employee = employeesDAO.readEmployeeByEmail(email);
            userInDatabase = true;
            if (passwordCorrect(employee.getPassword()))
                userController = new EmployeeController(employee);
            else
                view.print("Incorrect password");
        }
    }

    private void setControllerIfEmailOnManagersList(String email){
        List<String> managersEmails = managersDAO.readManagersEmails();

        if (managersEmails.contains(email)) {
            Manager manager = managersDAO.readManagerByEmail(email);
            userInDatabase = true;
            if (passwordCorrect(manager.getPassword()))
                userController = new ManagerController(manager);
            else
                view.print("Incorrect password");
        }
    }
}
