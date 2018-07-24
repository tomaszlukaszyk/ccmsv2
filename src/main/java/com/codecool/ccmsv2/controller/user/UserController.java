package com.codecool.ccmsv2.controller.user;

import com.codecool.ccmsv2.controller.dao.XMLStudentsDAO;
import com.codecool.ccmsv2.model.Student;
import com.codecool.ccmsv2.model.User;

import java.util.List;

public abstract class UserController {
    private User user;

    public UserController(User user) {
        this.user = user;
    }

    public abstract void startUserSession();

    public void showStudens(){
        List<Student> students = new XMLStudentsDAO().readStudents();
        for(Student student :students){
            System.out.println(student.getName() + " " + student.getEmail());
        }
    }

    private void welcomeUser(){
        System.out.println("Welcome" + user.getName());
    }
}
