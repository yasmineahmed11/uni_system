package com.uni.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "students")

public class User {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long id;

    //    for name
    @Setter
    @Getter
    @NotBlank(message = "Student name is required")
    @Size(min = 3, message = "Student name must be at least 3 characters")
    @Column(name = "student_name", nullable = false)
    private String studentName;


    @Min(value = 1, message = "Student level must be at least 1")
    @Max(value = 4, message = "Student level must be at most 4")
    @Column(name = "student_level", nullable = false)
    private int studentLevel;


    public  User(){}

    public User(String studentName, Integer studentLevel){
        this.studentName = studentName;
        this.studentLevel = studentLevel;
    }

    //    for student level


    public Integer getStudentLevel() {
        return studentLevel;
    }

    public void setStudentLevel(Integer student_level) {
        this.studentLevel = student_level;
    }

//    faculty relationship
    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "faculty_id")
    @JsonBackReference
    private Faculty faculty;

}
