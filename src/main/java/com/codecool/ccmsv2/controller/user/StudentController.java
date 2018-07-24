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
        String name = getView().getString();
        System.out.println(new CSVAssignmentsDAO().getAssignmentDescriptionByName(name));
    }

    private void submitAssignment(){
        String name = getView().getString();
        XMLStudentsDAO xmlStudentsDAO = new XMLStudentsDAO();
        List<Assignment> assignmentList = xmlStudentsDAO.readStudentAssignments(getUser().getEmail());
        Assignment assignmentToSubmit = findAssignment(name, assignmentList);
        String submissionLink = getView().getString();
        assignmentToSubmit.setSubmissionLink(submissionLink);
        xmlStudentsDAO.writeStudents(xmlStudentsDAO.readStudents(), assignmentList);
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
