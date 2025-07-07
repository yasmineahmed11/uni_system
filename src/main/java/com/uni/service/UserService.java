package com.uni.service;

import com.uni.model.User;
import com.uni.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepo;

    @Autowired
    public UserService(UserRepository userRepo){
        this.userRepo = userRepo;
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

    public User addUser(User user){
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
}
