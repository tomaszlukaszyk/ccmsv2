package com.codecool.ccmsv2.controller.user;

import com.codecool.ccmsv2.controller.dao.CSVAssignmentsDAO;
import com.codecool.ccmsv2.controller.dao.XMLStudentsDAO;
import com.codecool.ccmsv2.model.Assignment;
import com.codecool.ccmsv2.model.Mentor;
import com.codecool.ccmsv2.model.Student;

import java.util.List;

public class MentorController extends UserController {

    private CSVAssignmentsDAO csvAssignmentsDAO;
    private XMLStudentsDAO xmlStudentsDAO;

    public MentorController(Mentor mentor){
        super(mentor);
        csvAssignmentsDAO = new CSVAssignmentsDAO();
        xmlStudentsDAO = new XMLStudentsDAO();
    }

    private void showAssignments(){
        List<Assignment> assignments = csvAssignmentsDAO.readAssignments();
        for (Assignment assignment :assignments){
            getView().print(assignment.toString());
        }
    }

    private void addAssignment(){
        List<Assignment> assignments = csvAssignmentsDAO.readAssignments();
        assignments.add(new Assignment
                (getView().getInputString("Name?"), getView().getInputString("Description?")));
        csvAssignmentsDAO.writeAssignments(assignments);
    }

    public void gradeAssignment(){
        List<Student> students= xmlStudentsDAO.readStudents();
        Student student = chooseStudent(students);
        List<Assignment> studentsAssig = xmlStudentsDAO.readStudentAssignments(student.getEmail());
        Assignment assignment = chooseAssignment(studentsAssig);
        assignment.setGrade(getView().getInputString("Grade?"));
        xmlStudentsDAO.writeStudents(student, assignment);
    }

    public void startUserSession(){

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
}

