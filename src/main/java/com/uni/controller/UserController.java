package com.uni.controller;

import com.uni.model.User;
import com.uni.dto.UserRequest;
import com.uni.dto.UserResponse;
import com.uni.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/students")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService= userService;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public List<UserResponse> getAllUsers(){
        return userService.getAllUsers().stream()
                .map(userService::mapToUserResponse)
                .toList();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id){
        return userService.getUserById(id)
                .map(userService::mapToUserResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/level/{studentLevel}")
    public List<UserResponse> getUserByLevel(@PathVariable int studentLevel){
        return userService.getUserByLevel(studentLevel).stream()
                .map(userService::mapToUserResponse)
                .toList();
    }


//    new user
@PreAuthorize("isAuthenticated()")
@PostMapping("/batch")
public ResponseEntity<List<UserResponse>> addUsers(@Valid @RequestBody List<UserRequest> userRequests) {
    List<UserResponse> responses = userRequests.stream()
            .map(userRequest -> userService.addUser(userRequest))
            .map(userService::mapToUserResponse)
            .collect(Collectors.toList());
    return ResponseEntity.status(201).body(responses);
}


//    update a user or student
@PreAuthorize("isAuthenticated()")
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id,@Valid @RequestBody User updatedUser){
        return userService.updateUser(id, updatedUser)
                .map(userService::mapToUserResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
