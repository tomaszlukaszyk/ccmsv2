package com.codecool.ccmsv2;

import com.codecool.ccmsv2.controller.dao.XMLStudentsDAO;

public class Main {

    public static void main(String[] args){
    String filePath = Main.class.getResource("/users/students.xml").getPath();
    XMLStudentsDAO studentsDAO = new XMLStudentsDAO(filePath);
    System.out.println(studentsDAO.readStudents());
    System.out.println(studentsDAO.readStudentsEmails());
    System.out.println(studentsDAO.readStudentByEmail("student@codecool.com"));
    studentsDAO.writeStudents(studentsDAO.readStudents());
    }
}


