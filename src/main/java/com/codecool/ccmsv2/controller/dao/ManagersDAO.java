package com.codecool.ccmsv2.controller.dao;

import com.codecool.ccmsv2.model.Manager;

import java.util.List;

public interface ManagersDAO {
    List<String> readManagersEmails();
    Manager readManagerByEmail(String email);
}
