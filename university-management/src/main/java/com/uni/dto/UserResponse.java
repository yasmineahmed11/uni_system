package com.uni.dto;

public class UserResponse {

    private Long studentId;
    private String studentName;
    private Integer studentLevel;
    private String facultyName;

    public UserResponse() {}

    public UserResponse(Long studentId, String studentName, Integer studentLevel, String facultyName) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentLevel = studentLevel;
        this.facultyName = facultyName;
    }

    // Getters and Setters
    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getStudentLevel() {
        return studentLevel;
    }

    public void setStudentLevel(Integer studentLevel) {
        this.studentLevel = studentLevel;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }
}
