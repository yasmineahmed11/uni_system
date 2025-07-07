package com.uni.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnore;


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

    @JsonIgnore  //to not show el message
    @AssertTrue(message = "Student level must be 1 int")
    public boolean isStudentLevelValid() {
        return this.studentLevel == 1;
    }
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
}
