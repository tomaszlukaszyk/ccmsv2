package com.codecool.ccmsv2.controller.user;

import com.codecool.ccmsv2.controller.dao.XMLStudentsDAO;
import com.codecool.ccmsv2.model.Employee;

public class EmployeeController extends UserController {

    public EmployeeController(Employee employee) {
        super(employee);
    }

    public void startUserSession() {
        int option = 1;
        while (!(option == 0)) {

            getView().printMenu("Exit",
                    "Show Students");
            option = getView().getInputInt(0, 1);

            switch (option) {
                case 1:
                    showStudents();
                    break;
            }
        }
    }

    private void showStudents(){
        showUsers(new XMLStudentsDAO().readStudents());
    }
}
