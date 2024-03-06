package com.example.demo.controller;

import com.example.demo.dto.security.AccountCredentialsDTO;
import com.example.demo.dto.security.NewAccountDTO;
import com.example.demo.services.AuthService;
import com.example.demo.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Tag(name = "authentication Endpoint", description = "Endpoints for authentication and authorization.")
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthService authService;
    @Autowired
    UserService userService;

    @Autowired
    private DelegatingPasswordEncoder passwordEncoder;

    @SuppressWarnings("rawtypes")
    @Operation(summary = "Login", description = "Login with username and password to get a token.")
    @PostMapping("/signin")
    public ResponseEntity login(@RequestBody AccountCredentialsDTO data) {
        if (data == null || data.getUsername() == null || data.getUsername().isBlank() || data.getPassword() == null || data.getPassword().isBlank()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request");
        }
        var token = authService.login(data);
        if (token == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request");
        }
        return token;
    }

    @SuppressWarnings("rawtypes")
    @Operation(summary = "Refresh Token", description = "Create a new user token")
    @PutMapping("/refresh/{username}")
    public ResponseEntity refreshToken(@PathVariable String username, @RequestHeader("Authorization") String refreshToken) {
        if (refreshToken == null || username == null || username.isBlank() || refreshToken.isBlank()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request");
        }
        var token = authService.refreShToken(username, refreshToken);
        if (token == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request");
        }
        return token;
    }

    @SuppressWarnings("rawtypes")
    @Operation(summary = "Sign Up", description = "Create a new user account")
    @PostMapping("/signup")
    public ResponseEntity createAccount(@RequestBody NewAccountDTO data) {
        if (data == null || data.getUsername() == null || data.getUsername().isBlank() || data.getPassword() == null || data.getPassword().isBlank()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request");
        }
        userService.CreateAccount(data);
        return ResponseEntity.status(HttpStatus.CREATED).body("Account created");
    }
}
