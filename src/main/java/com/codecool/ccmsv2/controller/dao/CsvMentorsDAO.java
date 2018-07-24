package com.codecool.ccmsv2.controller.dao;

import com.codecool.ccmsv2.model.Mentor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CsvMentorsDAO implements MentorsDAO {

    final String filePath = CsvMentorsDAO.class.getClassLoader().getResource("users/mentors.csv").getPath();
    final int EMAIL_COL = 0;
    final int NAME_COL = 1;
    final int PASSWORD_COL = 2;

    @Override
    public List<String> readMentorsEmails() {

        List<String> emails = new ArrayList<>();
        Scanner fileReader = getScanner();

        while (fileReader.hasNext()) {
            String[] line = fileReader.nextLine().split("\\|");
            emails.add(line[EMAIL_COL]);
        }

        return emails;
    }

    @Override
    public Mentor readMentorByEmail(String email) {
        return null;
    }

    @Override
    public List<Mentor> readMentors() {
        return null;
    }

    @Override
    public void writeMentors(List<Mentor> mentors) {

    }

    private Scanner getScanner() {

        Scanner fileReader = null;

        try {
            fileReader = new Scanner(new File(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return fileReader;
    }
}
