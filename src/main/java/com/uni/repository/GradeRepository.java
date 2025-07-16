package com.uni.repository;

import com.uni.model.Grade;
import com.uni.model.User;
import com.uni.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
    List<Grade> findByStudent(User student);
    List<Grade> findByCourse(Course course);
}
