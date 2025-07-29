package com.uni.service;

import com.uni.dto.UserRequest;
import com.uni.model.Faculty;
import com.uni.model.User;
import com.uni.repository.UserRepository;
import com.uni.repository.FacultyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private UserRepository userRepo;
    private FacultyRepository facultyRepo;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepo = mock(UserRepository.class);
        facultyRepo = mock(FacultyRepository.class);
        userService = new UserService(userRepo, facultyRepo);
    }

//    test for all users showing
    @Test
    void testGetAllUsers() {
        // fake data
        User user1 = new User();
        user1.setStudentName("Yasmine");
        user1.setStudentLevel(2);

        User user2 = new User();
        user2.setStudentName("Ahmed");
        user2.setStudentLevel(3);

        List<User> mockUsers = Arrays.asList(user1, user2);
        when(userRepo.findAll()).thenReturn(mockUsers);

        // ely h3mlo: call the service method
        List<User> result = userService.getAllUsers();

        // check if el results are correct
        assertEquals(2, result.size());
        assertEquals("Yasmine", result.get(0).getStudentName());
        verify(userRepo, times(1)).findAll();
    }

//    test for adding a user
@Test
void testAddUser() {
    // Arrange
    UserRequest userRequest = new UserRequest();
    userRequest.setStudentName("Ali");
    userRequest.setStudentLevel(3);
    userRequest.setFacultyId(5L);

    Faculty faculty = new Faculty();
    faculty.setFacultyName("Computer Science"); 

    User userToSave = new User();
    userToSave.setStudentName("Ali");
    userToSave.setStudentLevel(3);
    userToSave.setFaculty(faculty);

    User savedUser = new User();
    savedUser.setStudentName("Ali");
    savedUser.setStudentLevel(3);
    savedUser.setFaculty(faculty);

    when(facultyRepo.findById(5L)).thenReturn(Optional.of(faculty));
    when(userRepo.save(any(User.class))).thenReturn(savedUser);

    // Act
    User result = userService.addUser(userRequest);

    // Assert
    assertNotNull(result);
    assertEquals("Ali", result.getStudentName());
    assertEquals(3, result.getStudentLevel());
    assertEquals("Computer Science", result.getFaculty().getFacultyName());
}

}
