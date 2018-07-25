package com.codecool.ccmsv2.controller.user;

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
            option = getView().getInputInt(0, 4);

            switch (option) {
                case 1:
                    showStudens();
                    option = getView().getInputInt(0, 1);
            }
        }
    }
}
