package com.uni.controller;

import com.uni.model.User;
import com.uni.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService= userService;
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/level/{studentLevel}")
    public List<User> getUserByLevel(@PathVariable int studentLevel){
        return userService.getUserByLevel(studentLevel);
    }


//    new user
    @PostMapping
    public ResponseEntity<User> addUser(@Valid  @RequestBody User user){
        User savedUser = userService.addUser(user);
        return ResponseEntity.status(201).body(savedUser);
    }

//    update a user or student
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id,@Valid @RequestBody User updatedUser){
        return userService.updateUser(id, updatedUser)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
