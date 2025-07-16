package com.uni.service;

import com.uni.model.User;
import com.uni.model.Faculty;
import com.uni.dto.UserRequest;
import com.uni.dto.UserResponse;
import  com.uni.repository.FacultyRepository;
import com.uni.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepo;
    private final FacultyRepository facultyRepo;

    @Autowired
    public UserService(UserRepository userRepo, FacultyRepository facultyRepo){
        this.userRepo = userRepo;
        this.facultyRepo = facultyRepo;
    }

    public List<User> getAllUsers(){
        return userRepo.findAll();
    }
    public Optional<User> getUserById(Long id){
        return userRepo.findById(id);
    }
    public List<User> getUserByLevel(int studentLevel){
        return userRepo.findByStudentLevel(studentLevel);
    }

    public User addUser(UserRequest userRequest){
        User user = new User();
        user.setStudentName(userRequest.getStudentName());
        user.setStudentLevel(userRequest.getStudentLevel());

        Faculty faculty = facultyRepo.findById(userRequest.getFacultyId())
                .orElseThrow(() -> new RuntimeException("Faculty not found"));

        user.setFaculty(faculty);

        return userRepo.save(user);
    }

    public void deleteUser(Long id){
        userRepo.deleteById(id);
    }
    public Optional<User> updateUser(Long id, User newUser){
        return userRepo.findById(id).map(existingUser -> {
            existingUser.setStudentName(newUser.getStudentName());
            existingUser.setStudentLevel(newUser.getStudentLevel());
            return userRepo.save(existingUser);
        });
    }

    public UserResponse mapToUserResponse(User user) {
        UserResponse response = new UserResponse();
        response.setStudentId(user.getId());
        response.setStudentName(user.getStudentName());
        response.setStudentLevel(user.getStudentLevel());

        if (user.getFaculty() != null) {
            response.setFacultyName(user.getFaculty().getFacultyName());
        }

        return response;
    }

}
