package com.codecool.ccmsv2.controller.dao;

import com.codecool.ccmsv2.model.Mentor;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CsvMentorsDAO implements MentorsDAO {

    final String filePath = CsvMentorsDAO.class.getClassLoader().getResource("users/mentors.csv").getPath();
    final int EMAIL_COL = 0;
    final int NAME_COL = 1;
    final int PASSWORD_COL = 2;

    Scanner fileReader;

    @Override
    public List<String> readMentorsEmails() {
        return null;
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
}
