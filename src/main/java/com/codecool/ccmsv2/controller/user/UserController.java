package com.codecool.ccmsv2.controller.user;

import com.codecool.ccmsv2.controller.dao.CsvEmployeesDAO;
import com.codecool.ccmsv2.controller.dao.CsvManagersDAO;
import com.codecool.ccmsv2.controller.dao.CsvMentorsDAO;
import com.codecool.ccmsv2.controller.dao.XMLStudentsDAO;
import com.codecool.ccmsv2.model.Student;
import com.codecool.ccmsv2.model.User;
import com.codecool.ccmsv2.view.View;

import java.util.List;

public abstract class UserController {
    private User user;
    private View view;

    public UserController(User user) {
        this.user = user;
        this.view = new View();
    }

    public abstract void startUserSession();

    public void showStudens(){
        List<Student> students = new XMLStudentsDAO().readStudents();
        for(Student student :students){
            System.out.println(student.getName() + " " + student.getEmail());

        }
        view.waitForConfirm();
    }

    public void welcomeUser(){
        System.out.println("Welcome" + user.getName());
    }

    public View getView(){
        return view;
    }

    public User getUser() {
        return user;
    }

    public boolean isEmailUnique(String email){
        boolean isEmailUnique = true;
        List<String> studEmails = new XMLStudentsDAO().readStudentsEmails();
        List<String> mentorsEmails = new CsvMentorsDAO().readMentorsEmails();
        List<String> employeesEmails = new CsvEmployeesDAO().readEmployeesEmails();
        List<String> managersEmails = new CsvManagersDAO().readManagersEmails();
        if (studEmails.contains(email)) {return false;}
        if (mentorsEmails.contains(email)) {return false;}
        if (employeesEmails.contains(email)) {return false;}
        if (managersEmails.contains(email)) {return false;}
        return isEmailUnique;
    }
}
