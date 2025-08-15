package com.uni.dto;

public class GradeResponse {
    private Long gradeId;
    private String studentName;
    private String courseName;
    private Integer score;

    public GradeResponse() {}

    public GradeResponse(Long gradeId, String studentName, String courseName, Integer score) {
        this.gradeId = gradeId;
        this.studentName = studentName;
        this.courseName = courseName;
        this.score = score;
    }

    // Getters w setters
    public Long getGradeId() {
        return gradeId;
    }

    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
