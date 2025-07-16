package com.uni.dto;

public class CourseRequest {
    private String courseName;
    private Integer studentLevel;
    private Long facultyId;

    public CourseRequest() {}

    public CourseRequest(String courseName, Integer studentLevel, Long facultyId) {
        this.courseName = courseName;
        this.studentLevel = studentLevel;
        this.facultyId = facultyId;
    }

    // Getters w setters
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getStudentLevel() {
        return studentLevel;
    }

    public void setStudentLevel(Integer studentLevel) {
        this.studentLevel = studentLevel;
    }

    public Long getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Long facultyId) {
        this.facultyId = facultyId;
    }
}
