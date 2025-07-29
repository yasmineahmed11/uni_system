package com.uni.controller;

import com.uni.dto.AuthRequest;
import com.uni.dto.AuthResponse;
import com.uni.dto.RegisterRequest;
import com.uni.model.Admin;
import com.uni.repository.AdminRepository;
import com.uni.service.AdminDetailsService;
import com.uni.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final AdminDetailsService adminDetailsService;
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

//    register
@PostMapping("/register")
public ResponseEntity<Map<String, String>> register(@RequestBody RegisterRequest request) {
    if (adminRepository.findByUsername(request.getUsername()).isPresent()) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(Map.of("message", "Username already exists"));
    }

    Admin admin = new Admin();
    admin.setUsername(request.getUsername());
    admin.setPassword(passwordEncoder.encode(request.getPassword()));
    admin.setRole(request.getRole());

    adminRepository.save(admin);

    return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(Map.of("message", "Admin registered successfully"));
}



// login
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        UserDetails userDetails = adminDetailsService.loadUserByUsername(request.getUsername());
        String token = jwtService.generateToken(userDetails);
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
