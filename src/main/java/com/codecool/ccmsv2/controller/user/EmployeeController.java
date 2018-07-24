package com.codecool.ccmsv2.controller.user;

import com.codecool.ccmsv2.model.Employee;

public class EmployeeController extends UserController {

    public EmployeeController(Employee employee) {
        super(employee);
    }

    public void startUserSession() {
        getView().printMenu("Exit",
                "Show Students");
        int option = getView().getInputInt(0, 4);
        while (!(option == 0)) {
            switch (option) {
                case 1:
                    showStudens();
                    option = getView().getInputInt(0, 1);
            }
        }
    }
}
