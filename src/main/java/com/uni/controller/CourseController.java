package com.uni.controller;

import com.uni.dto.CourseRequest;
import com.uni.dto.CourseResponse;
import com.uni.dto.UserRequest;
import com.uni.dto.UserResponse;
import com.uni.model.Course;
import com.uni.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import  com.uni.config.CorsConfig;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // Get all courses
    @GetMapping
    public List<CourseResponse> getAllCourses() {
        return courseService.getAllCourses()
                .stream()
                .map(courseService::mapToResponse)
                .toList();
    }

    // get course by ID
    @GetMapping("/{id}")
    public ResponseEntity<CourseResponse> getCourseById(@PathVariable Long id) {
        return courseService.getCourseById(id)
                .map(courseService::mapToResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get courses by faculty ID
    @GetMapping("/faculty/{facultyId}")
    public List<CourseResponse> getCoursesByFaculty(@PathVariable Long facultyId) {
        return courseService.getCoursesByFaculty(facultyId)
                .stream()
                .map(courseService::mapToResponse)
                .toList();
    }

    // Add new course
    @PostMapping("/batch")
    public ResponseEntity<List<CourseResponse>> addCourse(@Valid @RequestBody List<CourseRequest> courseRequests) {
        List<CourseResponse> responses = courseRequests.stream()
                .map(courseService::addCourse)
                .map(courseService::mapToResponse)
                .collect(Collectors.toList());

        return ResponseEntity.status(201).body(responses);
    }


    // Update an existing course
    @PutMapping("/{id}")
    public ResponseEntity<CourseResponse> updateCourse(
            @PathVariable Long id,
            @Valid @RequestBody CourseRequest courseRequest) {

        Course updatedCourse = courseService.updateCourse(id, courseRequest);
        CourseResponse response = courseService.mapToResponse(updatedCourse);
        return ResponseEntity.ok(response);
    }


    // Delete a course
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }

//    pagination
// GET /courses?page=0&size=5
//@GetMapping
//public ResponseEntity<List<CourseResponse>> getPaginatedCourses(
//        @RequestParam(defaultValue = "0") int page,
//        @RequestParam(defaultValue = "5") int size) {
//
//    Pageable pageable = PageRequest.of(page, size);
//    Page<Course> coursePage = courseService.getPaginatedCourses(pageable);
//
//    List<CourseResponse> responses = coursePage.getContent()
//            .stream()
//            .map(courseService::mapToResponse)
//            .toList();
//
//    return ResponseEntity.ok(responses);
//}

}
