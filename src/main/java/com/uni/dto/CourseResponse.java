package com.uni.dto;

public class CourseResponse {
    private Long courseId;
    private String courseName;
    private Integer studentLevel;
    private String facultyName;

    public CourseResponse() {}

    public CourseResponse(Long courseId, String courseName, Integer studentLevel, String facultyName) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.studentLevel = studentLevel;
        this.facultyName = facultyName;
    }

    // Getters w setters
    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

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

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }
}
