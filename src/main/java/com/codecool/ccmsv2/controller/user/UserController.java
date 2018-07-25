package com.codecool.ccmsv2.controller.user;

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
}
