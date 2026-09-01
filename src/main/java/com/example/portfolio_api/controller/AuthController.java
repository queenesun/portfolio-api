package com.example.portfolio_api.controller;

import com.example.portfolio_api.dto.LoginRequest;
import com.example.portfolio_api.dto.SignupRequest;
import com.example.portfolio_api.dto.UserResponse;
import com.example.portfolio_api.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@RequestBody SignupRequest request) {
        authService.signup(request);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        String token = authService.login(
                request.getEmail(),
                request.getPassword()
        );

        return ResponseEntity.ok(token);
    }

    @GetMapping("/me")
    public UserResponse me(Authentication authentication) {
        return authService.getUser(authentication.getName());
    }
}