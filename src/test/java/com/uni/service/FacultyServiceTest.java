package com.uni.service;

import com.uni.exception.ResourceNotFoundException;
import com.uni.model.Faculty;
import com.uni.repository.FacultyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FacultyServiceTest {

    @Mock
    private FacultyRepository facultyRepository;

    @InjectMocks
    private FacultyService facultyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

//    get all faculties
    @Test
    void getAllFaculties_ReturnsFacultyList() {
        List<Faculty> mockFaculties = Arrays.asList(
                new Faculty("Computer Science"),
                new Faculty("Business")
        );

        when(facultyRepository.findAll()).thenReturn(mockFaculties);

        List<Faculty> result = facultyService.getAllFaculties();

        assertEquals(2, result.size());
        assertEquals("Computer Science", result.get(0).getFacultyName());
    }


//    get faculty by id
//    @Test
//    void getFacultyById_ExistingId_ReturnsFacultyOptional() {
//        Faculty faculty = new Faculty("Engineering");
//
//        when(facultyRepository.findById(1L)).thenReturn(Optional.of(faculty));
//
//        Optional<Faculty> result = facultyService.getFacultyById(1L);
//
//        assertTrue(result.isPresent());
//        assertEquals("Engineering", result.get().getFacultyName());
//    }


//    id doesnt exist
//    @Test
//    void getFacultyById_NonExistingId_ReturnsEmptyOptional() {
//        when(facultyRepository.findById(99L)).thenReturn(Optional.empty());
//
//        Optional<Faculty> result = facultyService.getFacultyById(99L);
//
//        assertFalse(result.isPresent());
//    }


//    add faculty
    @Test
    void addFaculty_SavesAndReturnsFaculty() {
        Faculty faculty = new Faculty( "Arts");

        when(facultyRepository.save(faculty)).thenReturn(faculty);

        Faculty result = facultyService.addFaculty(faculty);

        assertEquals("Arts", result.getFacultyName());
        verify(facultyRepository, times(1)).save(faculty);
    }


//    delete faculty
    @Test
    void deleteFaculty_CallsRepositoryDeleteById() {
        Long facultyId = 1L;

        facultyService.deleteFaculty(facultyId);

        verify(facultyRepository, times(1)).deleteById(facultyId);
    }


//    exception


    @Test
    void testGetFacultyById_WhenExists() {
        Faculty faculty = new Faculty();
        faculty.setFacultyName("Engineering");

        when(facultyRepository.findById(1L)).thenReturn(Optional.of(faculty));

        Faculty result = facultyService.getFacultyById(1L);
        assertEquals("Engineering", result.getFacultyName());
    }

    @Test
    void testGetFacultyById_WhenNotExists() {
        when(facultyRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            facultyService.getFacultyById(99L);
        });
    }

}
