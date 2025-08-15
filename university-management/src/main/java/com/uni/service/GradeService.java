package com.uni.service;

import com.uni.dto.GradeRequest;
import com.uni.dto.GradeResponse;
import com.uni.model.Course;
import com.uni.model.Grade;
import com.uni.model.User;
import com.uni.repository.CourseRepository;
import com.uni.repository.GradeRepository;
import com.uni.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GradeService {

    private final GradeRepository gradeRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public GradeService(GradeRepository gradeRepository,
                        UserRepository userRepository,
                        CourseRepository courseRepository) {
        this.gradeRepository = gradeRepository;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    // Add grade
    public Grade addGrade(GradeRequest request) {
        User student = userRepository.findById(request.getStudentId())
                .orElseThrow(() -> new IllegalArgumentException("Student not found with ID: " + request.getStudentId()));

        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new IllegalArgumentException("Course not found with ID: " + request.getCourseId()));

        Grade grade = new Grade(student, course, request.getScore());
        return gradeRepository.save(grade);
    }

    // Get all grades
    public List<Grade> getAllGrades() {
        return gradeRepository.findAll();
    }

    // Get grades by student
    public List<Grade> getGradesByStudent(Long studentId) {
        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found with ID: " + studentId));
        return gradeRepository.findByStudent(student);
    }

    // Get grades by course
    public List<Grade> getGradesByCourse(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course not found with ID: " + courseId));
        return gradeRepository.findByCourse(course);
    }

    // Map to response DTO
    public GradeResponse mapToResponse(Grade grade) {
        return new GradeResponse(
                grade.getGradeId(),
                grade.getStudent().getStudentName(),
                grade.getCourse().getCourseName(),
                grade.getScore()
        );
    }

    // delete a grade
    public void deleteGrade(Long gradeId) {
        gradeRepository.deleteById(gradeId);
    }

    // update a grade
    public Optional<Grade> updateGrade(Long gradeId, Integer newScore) {
        return gradeRepository.findById(gradeId).map(grade -> {
            grade.setScore(newScore);
            return gradeRepository.save(grade);
        });
    }
}
