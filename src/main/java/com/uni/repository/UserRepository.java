package com.uni.repository;

import com.uni.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    List<User> findByStudentName(String studentName);
    List<User> findByStudentLevel(int studentLevel);
}
