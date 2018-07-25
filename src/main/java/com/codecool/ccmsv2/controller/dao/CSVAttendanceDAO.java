package com.codecool.ccmsv2.controller.dao;

import java.io.*;
import java.util.*;

public class CSVAttendanceDAO implements AttendanceDAO {
    private final String filePath = CSVAssignmentsDAO.class.getResource("/data/attendance.csv").getPath();
    private final int DATE_INDEX = 0;

    public Map<String, List<String>> readAttendance(){
        Map<String, List<String>> attendanceMap = new LinkedHashMap<>();
        Scanner fileReader = loadFile();
        while (fileReader.hasNext()) {
            String[] line = fileReader.nextLine().split("\\|");
            List<String> students = prepareStudentsList(line);
            attendanceMap.put(line[DATE_INDEX], students);
        }
        return attendanceMap;
    }

    public void writeAttendance(String date, List<String> students) {
        Map<String, List<String>> attendanceMap = readAttendance();
        if (!students.isEmpty()) {
            attendanceMap.put(date, students);
        }
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
                for (String key : attendanceMap.keySet()) {
                    String line = key + "|"+ concatenateStudents(attendanceMap.get(key));
                    writer.append(line + "\n");
                }
                writer.close();
            }
            catch (IOException e){
                e.printStackTrace();
            }

    }

    private String concatenateStudents(List<String> students){
        String concatenatedStudents = students.get(0);
        for (int i = 1; i<students.size(); i++){
            concatenatedStudents = concatenatedStudents + "|" + students.get(i);
        }
        return concatenatedStudents;
    }

    private List<String> prepareStudentsList(String[] line) {
        List<String> studentsAtDate = new ArrayList<>();
        for (int i = 1; i<line.length; i++){
            studentsAtDate.add(line[i]);
        }
        return studentsAtDate;
    }

    private Scanner loadFile(){
        File csvFile = new File(filePath);
        Scanner fileReader = null;
        try{
            fileReader = new Scanner(csvFile);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return fileReader;
    }
}


