package com.uni.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.List;

@Entity
@Table(name = "faculties")
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "faculty_id")
    private Long id;

    @Column(name = "faculty_name", nullable = false)
    private String facultyName;

    @OneToMany(mappedBy = "faculty", cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private List<User> students;



    public Faculty(){};

    public Faculty(String facultyName){
        this.facultyName= facultyName;
    }

//    getters & setters
    public Long getId(){
        return id;
    }

    public String getFacultyName(){
        return facultyName;
    }
    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

//    getters & setters lel students

    public List<User> getStudents() {
        return students;
    }

    public void setStudents(List<User> students) {
        this.students = students;
    }

//    courses relationship
    @OneToMany(mappedBy = "faculty")
    @JsonManagedReference
    private List<Course> courses;
}

