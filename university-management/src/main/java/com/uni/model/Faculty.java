package com.uni.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "faculties")
public class Faculty {

    //    getters & setters
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "faculty_id")
    private Long id;

    @Getter
    @Setter
    @Column(name = "faculty_name", nullable = false)
    private String facultyName;

    @Setter
    @Getter
    @OneToMany(mappedBy = "faculty", cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private List<User> students;



    public Faculty(){};

    public Faculty(String facultyName){
        this.facultyName= facultyName;
    }

//    getters & setters lel students

    //    courses relationship
    @OneToMany(mappedBy = "faculty")
    @JsonManagedReference
    private List<Course> courses;
}

