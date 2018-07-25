package com.codecool.ccmsv2.controller.user;

import com.codecool.ccmsv2.controller.dao.CSVAssignmentsDAO;
import com.codecool.ccmsv2.controller.dao.CSVAttendanceDAO;
import com.codecool.ccmsv2.controller.dao.XMLStudentsDAO;
import com.codecool.ccmsv2.model.Assignment;
import com.codecool.ccmsv2.model.Mentor;
import com.codecool.ccmsv2.model.Student;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class MentorController extends UserController {

    private CSVAssignmentsDAO csvAssignmentsDAO;
    private XMLStudentsDAO xmlStudentsDAO;
    private CSVAttendanceDAO csvAttendanceDAO;

    public MentorController(Mentor mentor){
        super(mentor);
        csvAssignmentsDAO = new CSVAssignmentsDAO();
        xmlStudentsDAO = new XMLStudentsDAO();
        csvAttendanceDAO = new CSVAttendanceDAO();
    }

    private void showAssignments(){
        List<Assignment> assignments = csvAssignmentsDAO.readAssignments();
        for (Assignment assignment :assignments){
            getView().print(assignment.toString() + "\n");
        }
        getView().waitForConfirm();
    }

    private void addAssignment(){
        List<Assignment> assignments = csvAssignmentsDAO.readAssignments();
        assignments.add(new Assignment
                (getView().getInputString("Name?"), getView().getInputString("Description?")));
        List<Student> students =xmlStudentsDAO.readStudents();
        xmlStudentsDAO.writeStudents(students, assignments);
        csvAssignmentsDAO.writeAssignments(assignments);
    }

    private void gradeAssignment(){
        List<Student> students= xmlStudentsDAO.readStudents();
        Student student = chooseStudent(students);
        List<Assignment> studentsAssig = xmlStudentsDAO.readStudentAssignments(student.getEmail());
        Assignment assignment = chooseAssignment(studentsAssig);
        assignment.setGrade(getView().getInputString("Grade?"));
        xmlStudentsDAO.writeStudents(student, assignment);
    }

    private void checkAttendance(){
        List<Student> students = xmlStudentsDAO.readStudents();
        List<String> presentStudents = new ArrayList<>();
        for (Student student : students){
            if (present(student)){
                presentStudents.add(student.getEmail());
            }
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String dateStr = dateFormat.format(date);
        csvAttendanceDAO.writeAttendance(dateStr, presentStudents);
    }

    private void addStudent(){
        List<Assignment>assignments = csvAssignmentsDAO.readAssignments();
        String email = setEmail();
        String name = getView().getInputString("Name?");
        String password = getView().getInputString("Password");
        Student student = new Student(email,name,password);
        xmlStudentsDAO.writeStudents(student, assignments);
    }

    private void removeStudent(){
        List<Student> students = xmlStudentsDAO.readStudents();
        Student student = chooseStudent(students);
        students.remove(student);
        xmlStudentsDAO.writeStudents(students);
    }

    private void editStudent(){
        List<Student> students = xmlStudentsDAO.readStudents();
        Student student = chooseStudent(students);
        student.setEmail(getView().getInputString("New Email?"));
        student.setName(getView().getInputString("New Name?"));
        student.setPassword(getView().getInputString("New Password?"));
        xmlStudentsDAO.writeStudents(student);
    }

    private String setEmail(){
        String email;
        do{
            email = getView().getInputString("Email?");
            getView().print("Such email exists in DBase");
        }while (!isEmailUnique(email));
        return email;
    }

    private void showAttendance(){
        Map<String, List<String>> attendance = new CSVAttendanceDAO().readAttendance();
        for (String key : attendance.keySet()){
            getView().print(key + attendance.get(key) + "\n");

        }
        getView().waitForConfirm();
    }


    public void startUserSession(){
        welcomeUser();
        while (!(option==0 )){

            getView().printMenu("Exit",
                    "Check Attendance",
                    "Show assignments",
                    "Grade assignments",
                    "Add assignment",
                    "Add Student",
                    "Remove Student",
                    "Edit Student",
                    "Show Students",
                    "Show Attendance");

            option = getView().getInputInt(0,9);

            switch (option){
                case 1:
                    checkAttendance();
                    break;
                case 2:
                    showAssignments();
                    break;
                case 3:
                    gradeAssignment();
                    break;
                case 4:
                    addAssignment();
                    break;
                case 5:
                    addStudent();
                    break;
                case 6:
                    removeStudent();
                    break;
                case 7:
                    editStudent();
                    break;
                case 8:
                    showStudens();
                    break;
                case 9:
                    showAttendance();
            }
        }
    }

    private Student chooseStudent(List<Student> students){
        for (int i =0; i<students.size(); i++){
            getView().print(i+1+ ". " + students.get(i).toString() + "\n");
        }
        int option = getView().getInputInt(1, students.size());
        return students.get(option-1);
    }

    private Assignment chooseAssignment(List<Assignment> assignmentsList){
        for (int i =0; i<assignmentsList.size(); i++){
            getView().print(i+1+ ". " + assignmentsList.get(i).toString()+ "\n");
        }
        int option = getView().getInputInt(1, assignmentsList.size());
        return assignmentsList.get(option-1);
    }

    private boolean present(Student student){
        getView().print("Is " + student.getName() + " present? \n 0/1");
        int option = getView().getInputInt(0,1);
        return (option != 0);
    }
}

