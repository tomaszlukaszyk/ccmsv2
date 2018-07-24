package com.codecool.ccmsv2.controller.user;

import com.codecool.ccmsv2.controller.dao.CsvMentorsDAO;
import com.codecool.ccmsv2.controller.dao.MentorsDAO;
import com.codecool.ccmsv2.controller.dao.StudentsDAO;
import com.codecool.ccmsv2.controller.dao.XMLStudentsDAO;
import com.codecool.ccmsv2.model.Manager;
import com.codecool.ccmsv2.model.Mentor;
import com.codecool.ccmsv2.model.Student;
import com.codecool.ccmsv2.model.User;

import java.util.List;

public class ManagerController extends UserController {

    private StudentsDAO studentsDAO;
    private MentorsDAO mentorsDAO;

    public ManagerController(Manager manager) {
        super(manager);
        studentsDAO = new XMLStudentsDAO();
        mentorsDAO = new CsvMentorsDAO();
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
                    showStudents();
                    break;
                case 2:
                    showMentors();
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

    private void showUsers(List<? extends User> users) {

        for (User user: users) {
            getView().print("\n" + user);
        }
    }

    private void showStudents() {
        List<Student> students = studentsDAO.readStudents();
        showUsers(students);
    }

    private void showMentors() {
        List<Mentor> mentors = mentorsDAO.readMentors();
        showUsers(mentors);
    }
}
