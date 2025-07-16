package com.uni.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;



@Entity
@Table(name = "students")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long id;

    @NotBlank(message = "Student name is required")
    @Size(min = 3, message = "Student name must be at least 3 characters")
    @Column(name = "student_name", nullable = false)
    private String studentName;

//    @JsonIgnore  //to not show el message
//    @AssertTrue(message = "Student level must be 1 int")
//    public boolean isStudentLevelValid() {
//        return this.studentLevel == 1;
//    }
    @Min(value = 1, message = "Student level must be at least 1")
    @Max(value = 4, message = "Student level must be at most 4")
    @Column(name = "student_level", nullable = false)
    private int studentLevel;


    public  User(){}

    public User(String studentName, Integer studentLevel){
        this.studentName = studentName;
        this.studentLevel = studentLevel;
    }

    public Long getId(){
        return id;
    }

//    for name
    public String getStudentName(){
        return studentName;
    }
    public void setStudentName(String studentName){
        this.studentName = studentName;
    }

//    for student level


    public Integer getStudentLevel() {
        return studentLevel;
    }

    public void setStudentLevel(Integer student_level) {
        this.studentLevel = student_level;
    }

//    faculty relationship
    @ManyToOne
    @JoinColumn(name = "faculty_id")
    @JsonBackReference
    private Faculty faculty;

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }
}
