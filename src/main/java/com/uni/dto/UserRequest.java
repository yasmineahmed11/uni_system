package com.uni.dto;

public class UserRequest {

    private String studentName;
    private Integer studentLevel;
    private Long facultyId;

    public UserRequest(){};

    public UserRequest(String studentName, Integer studentLevel, Long facultyId){
        this.facultyId =facultyId;
        this.studentLevel= studentLevel;
        this.studentName= studentName;
    }

//    getters & setters 
    public Long getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Long facultyId) {
        this.facultyId = facultyId;
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
}
