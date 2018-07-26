package com.codecool.ccmsv2.controller.dao;

import com.codecool.ccmsv2.model.Manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CsvManagersDAO implements ManagersDAO {

    private final String filePath = CsvManagersDAO.class.getClassLoader().getResource("users/managers.csv").getPath();
    private final int EMAIL_COL = 0;
    private final int NAME_COL = 1;
    private final int PASSWORD_COL = 2;

    @Override
    public List<String> readManagersEmails() {

        List<String> emails = new ArrayList<>();
        Scanner fileReader = getScanner();

        while (fileReader.hasNext()) {
            String[] line = fileReader.nextLine().split("\\|");
            emails.add(line[EMAIL_COL]);
        }

        return emails;
    }

    @Override
    public Manager readManagerByEmail(String email) {

        Scanner fileReader = getScanner();

        while (fileReader.hasNext()) {
            String[] line = fileReader.nextLine().split("\\|");

            if (line[EMAIL_COL].equals(email))
                return new Manager(line[NAME_COL], email, line[PASSWORD_COL]);
        }

        return null;
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
