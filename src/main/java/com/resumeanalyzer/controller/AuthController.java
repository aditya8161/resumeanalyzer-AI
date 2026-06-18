package com.resumeanalyzer.controller;

import com.resumeanalyzer.auth.AuthService;
import com.resumeanalyzer.common.ApiResponse;
import com.resumeanalyzer.dto.AuthResponse;
import com.resumeanalyzer.dto.LoginRequest;
import com.resumeanalyzer.dto.RegisterRequest;
import com.resumeanalyzer.entity.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController
{
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AuthResponse>> register(
            @Valid @RequestBody RegisterRequest request) {

        AuthResponse response = authService.register(request);
        return ResponseEntity.ok(
                ApiResponse.success(response, "Registration successful"));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(
            @Valid @RequestBody LoginRequest request) {

        AuthResponse response = authService.login(request);
        return ResponseEntity.ok(
                ApiResponse.success(response, "Login successful"));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<ApiResponse<AuthResponse>> refreshToken(
            @RequestParam String token) {

        AuthResponse response = authService.refreshToken(token);
        return ResponseEntity.ok(
                ApiResponse.success(response, "Token refreshed"));
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Void>> logout(
            @AuthenticationPrincipal User user) {

        authService.logout(user.getId());
        return ResponseEntity.ok(
                ApiResponse.success(null, "Logged out successfully"));
    }
}
