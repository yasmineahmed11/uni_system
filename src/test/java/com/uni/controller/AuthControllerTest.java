package com.uni.controller;

import com.uni.dto.AuthRequest;
import com.uni.dto.AuthResponse;
import com.uni.dto.RegisterRequest;
import com.uni.model.Admin;
import com.uni.repository.AdminRepository;
import com.uni.service.AdminDetailsService;
import com.uni.config.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthControllerTest {

    @InjectMocks
    private AuthController authController;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtService jwtService;

    @Mock
    private AdminDetailsService adminDetailsService;


    @Mock
    private AdminRepository adminRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

//    register
    @Test
    void testRegister_Success() {
        // Arrange
        RegisterRequest request = new RegisterRequest("newuser", "password123", "ADMIN");

        when(adminRepository.findByUsername("newuser")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("password123")).thenReturn("hashedPassword");

        // Act
        ResponseEntity<Map<String, String>> response = authController.register(request);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Admin registered successfully", response.getBody().get("message"));
        verify(adminRepository, times(1)).save(any(Admin.class));
    }

    @Test
    void testRegister_UsernameExists() {
        // Arrange
        RegisterRequest request = new RegisterRequest("existingUser", "password123", "ADMIN");

        when(adminRepository.findByUsername("existingUser")).thenReturn(Optional.of(new Admin()));

        // Act
        ResponseEntity<Map<String, String>> response = authController.register(request);

        // Assert
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Username already exists", response.getBody().get("message"));
        verify(adminRepository, never()).save(any(Admin.class));
    }


//    login
    @Test
    void login_ValidCredentials_ReturnsToken() {
        // Arrange
        AuthRequest loginRequest = new AuthRequest();
        loginRequest.setUsername("admin");
        loginRequest.setPassword("admin123");

        Authentication mockAuth = mock(Authentication.class);

        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withUsername("admin")
                .password("encodedPass")
                .roles("ADMIN")
                .build();

        when(authenticationManager.authenticate(any())).thenReturn(mockAuth);
        when(adminDetailsService.loadUserByUsername("admin")).thenReturn(userDetails);
        when(jwtService.generateToken(userDetails)).thenReturn("fake-jwt-token");

        // Act
        ResponseEntity<AuthResponse> response = authController.login(loginRequest);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("fake-jwt-token", response.getBody().getToken());
    }

//    invalid login credentials
@Test
void login_InvalidCredentials_ThrowsException() {
    // Arrange
    AuthRequest loginRequest = new AuthRequest();
    loginRequest.setUsername("wrong");
    loginRequest.setPassword("wrong123");

    when(authenticationManager.authenticate(any()))
            .thenThrow(new BadCredentialsException("Invalid credentials"));

    // Act & Assert
    assertThrows(BadCredentialsException.class, () -> {
        authController.login(loginRequest);
    });
}

}
