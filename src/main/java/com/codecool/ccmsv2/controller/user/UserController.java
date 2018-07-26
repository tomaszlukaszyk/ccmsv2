package com.codecool.ccmsv2.controller.user;

import com.codecool.ccmsv2.controller.dao.CsvEmployeesDAO;
import com.codecool.ccmsv2.controller.dao.CsvManagersDAO;
import com.codecool.ccmsv2.controller.dao.CsvMentorsDAO;
import com.codecool.ccmsv2.controller.dao.XMLStudentsDAO;
import com.codecool.ccmsv2.model.User;
import com.codecool.ccmsv2.view.View;

import java.util.List;

public abstract class UserController {
    private User user;
    private View view;

    UserController(User user) {
        this.user = user;
        this.view = new View();
    }

    public abstract void startUserSession();

    public void welcomeUser(){
        System.out.println("Welcome" + user.getName());
    }

    View getView(){
        return view;
    }

    public User getUser() {
        return user;
    }

    private boolean isEmailUnique(String email){
        List<String> studEmails = new XMLStudentsDAO().readStudentsEmails();
        List<String> mentorsEmails = new CsvMentorsDAO().readMentorsEmails();
        List<String> employeesEmails = new CsvEmployeesDAO().readEmployeesEmails();
        List<String> managersEmails = new CsvManagersDAO().readManagersEmails();
        if (studEmails.contains(email)) {return false;}
        if (mentorsEmails.contains(email)) {return false;}
        if (employeesEmails.contains(email)) {return false;}
        return !managersEmails.contains(email);
    }

    void showUsers(List<? extends User> users) {

        for (int i=0; i<users.size(); i++) {
            getView().print(String.format("\n%d. %s", i + 1, users.get(i)));
        }
        getView().print("\n");

    }

    String[] getBasicUserData() {

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

    int chooseUser(List<? extends User> users) {
        view.print("\n Choose a " + users.get(0).getClass().getName());
        showUsers(users);
        return getView().getInputInt(1, users.size()) - 1;
    }

    int chooseUserData() {
        String[] data = new String[]{"name", "email", "password"};

        getView().print("\nChoose data to edit:");
        for (int i=0; i<data.length; i++){
            getView().print(String.format("\n%d. %s", i + 1, data[i]));
        }

        return getView().getInputInt(1, data.length);
    }

    void updateChosenData(int chosenDataIndex, User user) {
        switch (chosenDataIndex) {
            case 1:
                getView().print("Old name: " + user.getName());
                String name = getView().getInputString("New name: ");
                user.setName(name);
                break;
            case 2:
                getView().print("Old email: " + user.getEmail());
                String email = getEmail("New email: ");
                user.setEmail(email);
                break;
            case 3:
                getView().print("Old password: " + user.getPassword());
                String password = getView().getInputString("New password: ");
                user.setPassword(password);
                break;
        }
    }
}
