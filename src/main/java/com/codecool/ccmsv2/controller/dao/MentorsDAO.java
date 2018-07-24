package com.codecool.ccmsv2.controller.dao;

import com.codecool.ccmsv2.model.Mentor;

import java.util.List;

public interface MentorsDAO {
    List<String> readMentorsEmails();
    Mentor readMentorByEmail(String email);
    List<Mentor> readMentors();
    void writeMentors(List<Mentor> mentors);
}
