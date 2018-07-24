package com.codecool.ccmsv2.controller.dao;

import com.codecool.ccmsv2.model.Assignment;

import java.util.List;

public interface AssignmentDAO {
    List<Assignment> readAssignments();
    void writeAssignments(List<Assignment> assignments);
    String getAssignmentDescriptionByName(String name);
}
