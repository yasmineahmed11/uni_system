package com.uni.controller;

import com.uni.dto.GradeRequest;
import com.uni.dto.GradeResponse;
import com.uni.model.Grade;
import com.uni.service.GradeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/grades")
public class GradeController {

    private final GradeService gradeService;

    @Autowired
    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    // Get all grades
    @GetMapping
    public List<GradeResponse> getAllGrades() {
        return gradeService.getAllGrades()
                .stream()
                .map(gradeService::mapToResponse)
                .toList();
    }

    // Get grades by student ID
    @GetMapping("/student/{studentId}")
    public List<GradeResponse> getGradesByStudent(@PathVariable Long studentId) {
        return gradeService.getGradesByStudent(studentId)
                .stream()
                .map(gradeService::mapToResponse)
                .toList();
    }

    // Get grades by course ID
    @GetMapping("/course/{courseId}")
    public List<GradeResponse> getGradesByCourse(@PathVariable Long courseId) {
        return gradeService.getGradesByCourse(courseId)
                .stream()
                .map(gradeService::mapToResponse)
                .toList();
    }

    // Add a new grade
    @PostMapping
    public ResponseEntity<GradeResponse> addGrade(@Valid @RequestBody GradeRequest request) {
        Grade grade = gradeService.addGrade(request);
        return ResponseEntity.status(201).body(gradeService.mapToResponse(grade));
    }

    // Update grade score
    @PutMapping("/{id}")
    public ResponseEntity<GradeResponse> updateGrade(@PathVariable Long id, @RequestParam Integer score) {
        return gradeService.updateGrade(id, score)
                .map(gradeService::mapToResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete grade
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGrade(@PathVariable Long id) {
        gradeService.deleteGrade(id);
        return ResponseEntity.noContent().build();
    }
}
