package com.codecool.ccmsv2.controller.dao;

import com.codecool.ccmsv2.model.Assignment;
import com.codecool.ccmsv2.model.Student;

import java.util.List;

public interface StudentsDAO {
    List<String> readStudentsEmails();
    Student readStudentByEmail(String email);
    List<Student> readStudents();
    void writeStudents(List<Student> students, List<Assignment> assignments);
    List<Assignment> readStudentAssignments(String email);
}
