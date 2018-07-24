package com.codecool.ccmsv2.controller.dao;

import com.codecool.ccmsv2.model.Assignment;
import com.codecool.ccmsv2.model.Student;
import org.w3c.dom.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class XMLStudentsDAO implements StudentsDAO {
    private String filePath = XMLStudentsDAO.class.getResource("/users/students.xml").getPath();
    private Document parsedXML;
    private Document parsedStudents;



    public List<String> readStudentsEmails(){
        loadXML();
        List<String> studentsEmails = new ArrayList<>();
        NodeList students = parsedXML.getElementsByTagName("Student");
        for (int i = 0; i<students.getLength(); i++){
            Element student = (Element) students.item(i);
            studentsEmails.add(student.getAttribute("email"));
        }
        return studentsEmails;
    }

    public Student readStudentByEmail(String email){
        loadXML();
        NodeList students = parsedXML.getElementsByTagName("Student");
        for (int i = 0; i<students.getLength(); i++){
            Element student = (Element) students.item(i);
            if (student.getAttribute("email").equals(email)){
                return createStudent(student);
            }
        }
        return null;
    }

    public List<Assignment> readStudentAssignments(String email){
        loadXML();
        List<Assignment> assignments = new ArrayList<>();
        NodeList students = parsedXML.getElementsByTagName("Student");
        for (int i = 0; i<students.getLength(); i++){
            Element student = (Element) students.item(i);
            assignments = populateAssignmentList(student, email);
        }
        return assignments;
    }

    public List<Student> readStudents(){
        loadXML();
        List<Student> studentsList = new ArrayList<>();
        NodeList students = parsedXML.getElementsByTagName("Student");
        for (int i=0; i<students.getLength(); i++){
            Element student = (Element) students.item(i);
            studentsList.add(createStudent(student));
        }
        return studentsList;
    }

    public void writeStudents(List<Student> students, List<Assignment> assignmentList){
        try {
            Element rootElement = prepareXMLStructure();
            for (Student stud : students){
                serializeStudentData(stud, rootElement, assignmentList);
            }
            exportToFile();
        }catch (ParserConfigurationException e){
            e.printStackTrace();
        }catch (TransformerException e){
            e.printStackTrace();
        }
    }

    private Element prepareXMLStructure() throws ParserConfigurationException{
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        parsedStudents = docBuilder.newDocument();
        Element rootElement = parsedStudents.createElement("Students");
        parsedStudents.appendChild(rootElement);
        return rootElement;
    }

    private void exportToFile() throws TransformerException{
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(parsedStudents);
        StreamResult result = new StreamResult(XMLStudentsDAO.class.getResource("/users/students.xml").getPath());

        transformer.transform(source, result);

        System.out.println("File saved!");
    }

    private void serializeAssignmentsData(Element student, List<Assignment> assignments){
        Element assignmentRoot = parsedStudents.createElement("Assignments");
        student.appendChild(assignmentRoot);
        for(Assignment assignment : assignments){
            Element eAssignment = parsedStudents.createElement("Assignment");
            assignmentRoot.appendChild(eAssignment);
            eAssignment.setAttribute("name", assignment.getName());
            Element subLink = parsedStudents.createElement("SubmissionLink");
            subLink.appendChild(parsedStudents.createTextNode(assignment.getSubmissionLink()));
            eAssignment.appendChild(subLink);
            Element grade = parsedStudents.createElement("Grade");
            grade.appendChild(parsedStudents.createTextNode(assignment.getGrade()));
            eAssignment.appendChild(grade);
        }
    }

    private void serializeStudentData(Student stud, Element rootElement, List<Assignment> assignmentList){
        Element student = parsedStudents.createElement("Student");
        rootElement.appendChild(student);
        student.setAttribute("email", stud.getEmail());
        Element name = parsedStudents.createElement("Name");
        name.appendChild(parsedStudents.createTextNode(stud.getName()));
        student.appendChild(name);
        Element password = parsedStudents.createElement("Password");
        password.appendChild(parsedStudents.createTextNode(stud.getPassword()));
        student.appendChild(password);
        serializeAssignmentsData(student, assignmentList);
    }


    private List<Assignment> populateAssignmentList(Element student, String email){
        List<Assignment> assignmentsList = new ArrayList<>();
        if (student.getAttribute("email").equals(email)){
            NodeList assignments = student.getElementsByTagName("Assignment");
            for(int i = 0; i<assignments.getLength(); i++){
                Element assignment = (Element) assignments.item(i);
                String assName = assignment.getAttribute("name");
                String submissionLink = assignment.getElementsByTagName("SubmissionLink").item(0).getTextContent();
                String grade = assignment.getElementsByTagName("Grade").item(0).getTextContent();
                String description = (new CSVAssignmentsDAO()).getAssignmentDescription(assName);
                assignmentsList.add(new Assignment(assName, description, submissionLink, grade));
            }
        }
        return assignmentsList;
    }

    private Student createStudent(Element student){
        String email = student.getAttribute("email");
        String name = student.getElementsByTagName("Name").item(0).getTextContent();
        String password = student.getElementsByTagName("Password").item(0).getTextContent();
        return new Student(name, email, password);
    }

    private void loadXML() {
        try {
            File xmlFile = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            parsedXML = dBuilder.parse(xmlFile);
            parsedXML.getDocumentElement().normalize();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
