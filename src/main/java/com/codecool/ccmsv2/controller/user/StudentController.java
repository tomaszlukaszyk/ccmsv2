package com.codecool.ccmsv2.controller.user;

import com.codecool.ccmsv2.controller.dao.CSVAssignmentsDAO;
import com.codecool.ccmsv2.controller.dao.XMLStudentsDAO;
import com.codecool.ccmsv2.model.Assignment;
import com.codecool.ccmsv2.model.Student;

import javax.xml.bind.annotation.XmlAccessOrder;
import java.util.List;

public class StudentController extends UserController {

    public StudentController(Student student){
        super(student);
    }

    private void showAssignmentDescription(){
        String name = getView().getInputString("Name of assignment?");
        System.out.println(name +"\n" + new CSVAssignmentsDAO().getAssignmentDescriptionByName(name));
    }

    private void submitAssignment(){
        String name = getView().getInputString("Name of assignment?");
        XMLStudentsDAO xmlStudentsDAO = new XMLStudentsDAO();
        List<Assignment> assignmentList = xmlStudentsDAO.readStudentAssignments(getUser().getEmail());
        Assignment assignmentToSubmit = findAssignment(name, assignmentList);
        String submissionLink = getView().getInputString("Linkt to assignment?");
        assignmentToSubmit.setSubmissionLink(submissionLink);
        xmlStudentsDAO.writeStudents(xmlStudentsDAO.readStudents(), assignmentList);
    }

    private void showGrades(){
        List<Assignment> assignmentList = new XMLStudentsDAO().readStudentAssignments(getUser().getEmail());
        for (Assignment assignment : assignmentList){
            System.out.println(assignment.getName() +":  " + assignment.getGrade());
        }
    }

    private void showAssignments(){
        List<Assignment> assignmentList = new XMLStudentsDAO().readStudentAssignments(getUser().getEmail());
        for (Assignment assignment : assignmentList){
            System.out.println(assignment);
        }
    }

    private Assignment findAssignment(String name, List<Assignment> assignments){
        for (Assignment assignment : assignments){
            if (assignment.getName().equals(name)){
                return assignment;
            }
        }
        return null;
    }
}
