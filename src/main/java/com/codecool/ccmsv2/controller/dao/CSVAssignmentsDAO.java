package com.codecool.ccmsv2.controller.dao;

import com.codecool.ccmsv2.model.Assignment;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVAssignmentsDAO implements AssignmentDAO {
    private String filePath = CSVAssignmentsDAO.class.getResource("/data/assignments.csv").getPath();
    private final int NAME_INDEX = 0;
    private final int DESCRIPTION_INDEX = 1;


    public List<Assignment> readAssignments(){
        List<Assignment> assignments = new ArrayList<>();
        Scanner fileReader = loadFile();
        while (fileReader.hasNext()){
            String[] line = fileReader.nextLine().split("\\|");
            assignments.add(new Assignment(line[NAME_INDEX], line[DESCRIPTION_INDEX]));
        }
        return assignments;
    }

    public String getAssignmentDescriptionByName(String name){
        Scanner fileReader = loadFile();
        while (fileReader.hasNext()){
            String[] line = fileReader.nextLine().split("\\|");
            if(line[NAME_INDEX].equals(name)){
                return line[DESCRIPTION_INDEX];
            }
        }
        return null;
    }

    public void writeAssignments(List<Assignment> assignments){
        List<String> data = prepareAssignmentsForSaving(assignments);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            for (String line : data) {
                writer.append(line).append("\n");
            }
                writer.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    private List<String> prepareAssignmentsForSaving(List<Assignment> assignments){
        List<String> preparedAssignment = new ArrayList<>();
        for(Assignment assignment : assignments){
            preparedAssignment.add(assignment.getName() + "|" +assignment.getDescription());
        }
        return preparedAssignment;
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
