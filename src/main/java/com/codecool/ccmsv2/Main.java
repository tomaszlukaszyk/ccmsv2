package com.codecool.ccmsv2;

import com.codecool.ccmsv2.controller.Logger;
import com.codecool.ccmsv2.view.View;

public class Main {

    public static void main(String[] args) {

        Logger logger = new Logger();
        View view = new View();
        boolean isRunning = true;
        int option;

        while (isRunning) {
            view.printMenu("Exit program", "Log in");
            option = view.getInputInt(0,1);

            switch (option) {
                case 0:
                    isRunning = false;
                    break;
                case 1:
                    logger.logIn();
                    break;
            }
        }
    }
}


