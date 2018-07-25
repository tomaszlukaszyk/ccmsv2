package com.codecool.ccmsv2.controller.user;

import com.codecool.ccmsv2.controller.dao.CSVAssignmentsDAO;
import com.codecool.ccmsv2.controller.dao.XMLStudentsDAO;
import com.codecool.ccmsv2.model.Assignment;
import com.codecool.ccmsv2.model.Student;

import java.util.List;

public class StudentController extends UserController {
    private XMLStudentsDAO xmlStudentsDAO;
    private Student student;

    public StudentController(Student student){
        super(student);
        this.student = student;
        xmlStudentsDAO = new XMLStudentsDAO();
    }


    public void startUserSession(){
        int option = 1;
        while (!(option==0 )){

            getView().printMenu("Exit",
                    "Assignment List",
                    "Grades",
                    "Get Assignment Details",
                    "Submit Assignment");

            option = getView().getInputInt(0,4);

            switch (option){
                case 1:
                    showAssignments();
                    break;
                case 2:
                    showGrades();
                    break;
                case 3:
                    showAssignmentDescription();
                    break;
                case 4:
                    submitAssignment();
                    break;
            }
        }
    }

    private void showAssignmentDescription(){
        List<Assignment> assignmentList = xmlStudentsDAO.readStudentAssignments(getUser().getEmail());
        String name = findAssignmentName(assignmentList);
        System.out.println(name +"\n" + new CSVAssignmentsDAO().getAssignmentDescriptionByName(name));
        getView().waitForConfirm();
    }

    private void submitAssignment(){
        List<Assignment> assignmentList = xmlStudentsDAO.readStudentAssignments(getUser().getEmail());
        Assignment assignmentToSubmit = findAssignment(assignmentList);
        String submissionLink = getView().getInputString("Link to assignment?");
        assignmentToSubmit.setSubmissionLink(submissionLink);
        xmlStudentsDAO.writeStudents(student, assignmentToSubmit);
    }

    private void showGrades(){
        List<Assignment> assignmentList = new XMLStudentsDAO().readStudentAssignments(getUser().getEmail());
        for (Assignment assignment : assignmentList){
            System.out.println(assignment.getName() +":  " + assignment.getGrade());
        }
        getView().waitForConfirm();
    }

    private void showAssignments(){
        List<Assignment> assignmentList = new XMLStudentsDAO().readStudentAssignments(getUser().getEmail());
        for (Assignment assignment : assignmentList){
            System.out.println(assignment.getName() + "\n" + assignment.getSubmissionLink());
        }
        getView().waitForConfirm();
    }

    private Assignment findAssignment(List<Assignment> assignments){
        String name = findAssignmentName(assignments);
        for (Assignment assignment : assignments){
            if (assignment.getName().equals(name)){
                return assignment;
            }
        }
        return null;
    }

    private String findAssignmentName(List<Assignment> assignmentsList){
        for (int i =0; i<assignmentsList.size(); i++){
            getView().print(i+1+ ". " + assignmentsList.get(i).getName() + "\n");
        }
        int option = getView().getInputInt(1, assignmentsList.size());
        return assignmentsList.get(option-1).getName();
    }
}
