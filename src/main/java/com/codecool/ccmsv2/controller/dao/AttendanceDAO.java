package com.codecool.ccmsv2.controller.dao;

import java.util.List;
import java.util.Map;

public interface AttendanceDAO {
    Map<String, List<String>> readAttendance();
    void writeAttendance(String date, List<String> students);
}
