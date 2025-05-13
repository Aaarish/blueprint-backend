package com.roya.blueprint_backend.auth.controller;

import com.roya.blueprint_backend.auth.service.AuthService;
import com.roya.blueprint_backend.auth.model.AuthDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthDto.AuthResponse> login(@RequestBody AuthDto.AuthRequest authRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.authenticate(authRequest));
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthDto.AuthResponse> signup(@RequestBody AuthDto.RegisterRequest registerRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(registerRequest));
    }

}
