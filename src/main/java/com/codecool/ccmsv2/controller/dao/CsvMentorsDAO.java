package com.codecool.ccmsv2.controller.dao;

import com.codecool.ccmsv2.model.Mentor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
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

        Scanner fileReader = getScanner();

        while (fileReader.hasNext()) {
            String[] line = fileReader.nextLine().split("\\|");

            if (line[EMAIL_COL].equals(email))
                return new Mentor(line[NAME_COL], email, line[PASSWORD_COL]);
        }

        return null;
    }

    @Override
    public List<Mentor> readMentors() {

        List<Mentor> mentors = new ArrayList<>();
        Scanner fileReader = getScanner();

        while (fileReader.hasNext()) {

            String[] line = fileReader.nextLine().split("\\|");
            mentors.add(new Mentor(line[NAME_COL], line[EMAIL_COL], line[PASSWORD_COL]));

        }

        return mentors;
    }

    @Override
    public void writeMentors(List<Mentor> mentors) {

        try (Formatter writer = new Formatter(filePath)) {

            for (Mentor mentor: mentors) {
                writer.format("%s|%s|%s\n", mentor.getEmail(), mentor.getName(), mentor.getPassword());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

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
