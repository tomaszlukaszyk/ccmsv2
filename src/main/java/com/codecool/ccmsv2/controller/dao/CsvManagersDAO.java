package com.codecool.ccmsv2.controller.dao;

import com.codecool.ccmsv2.model.Manager;

import java.util.List;

public class CsvManagersDAO implements ManagersDAO {

    final String filePath = CsvManagersDAO.class.getClassLoader().getResource("users/managers.csv").getPath();
    final int EMAIL_COL = 0;
    final int NAME_COL = 1;
    final int PASSWORD_COL = 2;

    @Override
    public List<String> readManagersEmails() {
        return null;
    }

    @Override
    public Manager readManagerByEmail(String email) {
        return null;
    }
}
