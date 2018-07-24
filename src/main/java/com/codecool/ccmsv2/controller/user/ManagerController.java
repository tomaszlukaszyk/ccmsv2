package com.codecool.ccmsv2.controller.user;

import com.codecool.ccmsv2.model.Manager;

public class ManagerController extends UserController {

    public ManagerController(Manager manager) {
        super(manager);
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
                    // TODO: show students
                    break;
                case 2:
                    // TODO: show mentors
                    break;
                case 3:
                    // TODO: add mentor
                    break;
                case 4:
                    // TODO: edit mentor
                    break;
                case 5:
                    // TODO: remove mentor
                    break;
                case 6:
                    // TODO: show employees
                    break;
                case 7:
                    // TODO: add employee
                    break;
                case 8:
                    // TODO: edit employee
                    break;
                case 9:
                    // TODO: remove employee
                    break;
            }
        }

    }
}
