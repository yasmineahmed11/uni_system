package com.uni.service;

import com.uni.dto.CourseRequest;
import com.uni.dto.CourseResponse;
import com.uni.model.Course;
import com.uni.model.Faculty;
import com.uni.repository.CourseRepository;
import com.uni.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;



import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final FacultyRepository facultyRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository, FacultyRepository facultyRepository) {
        this.courseRepository = courseRepository;
        this.facultyRepository = facultyRepository;
    }

    // Add a new course
    public Course addCourse(CourseRequest request) {
        Faculty faculty = facultyRepository.findById(request.getFacultyId())
                .orElseThrow(() -> new IllegalArgumentException("Faculty not found with ID: " + request.getFacultyId()));

        Course course = new Course();
        course.setCourseName(request.getCourseName());
        course.setStudentLevel(request.getStudentLevel());
        course.setFaculty(faculty);

        return courseRepository.save(course);
    }

    // Get all courses
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Page<Course> getPaginatedCourses(Pageable pageable) {
        return courseRepository.findAll(pageable);
    }



    // Map entity to DTO
    public CourseResponse mapToResponse(Course course) {
        return new CourseResponse(
                course.getCourseId(),
                course.getCourseName(),
                course.getStudentLevel(),
                course.getFaculty().getFacultyName()
        );
    }

    // Get one course by ID
    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    // Get courses by faculty
    public List<Course> getCoursesByFaculty(Long facultyId) {
        return courseRepository.findByFaculty_Id(facultyId);
    }

//    update
    public Course updateCourse(Long id, CourseRequest request) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        course.setCourseName(request.getCourseName());
//        course.setFaculty(facultyRepository.findById(request.getFacultyId())
//                .orElseThrow(() -> new RuntimeException("Faculty not found")));

        return courseRepository.save(course);
    }


    // Delete course
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
}
