package com.codecool.ccmsv2.model;

public class Assignment {

    private String name;
    private String description;
    private String submissionLink;
    private String grade;

    public Assignment(String name, String description, String submissionLink, String grade) {
        this.name = name;
        this.description = description;
        this.submissionLink = submissionLink;
        this.grade = grade;
    }

    public Assignment(String name, String description) {
        this.name = name;
        this.description = description;
        this.submissionLink = "";
        this.grade = "0";
    }


    public String getName() {
        return name;
    }

    public String getDescription(){
        return description;
    }

    public String getSubmissionLink() {
        return submissionLink;
    }

    public void setSubmissionLink(String submissionLink) {
        this.submissionLink = submissionLink;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String toString() {
        return String.format("%s\n%s", this.name, this.description);
    }
}
