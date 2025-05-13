package com.roya.blueprint_backend.auth.service;

import com.roya.blueprint_backend.auth.model.AuthDto;
import com.roya.blueprint_backend.users.User;
import com.roya.blueprint_backend.users.UserDto;
import com.roya.blueprint_backend.users.UserRepository;
import com.roya.blueprint_backend.users.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    private final UserService userService;
    private final UserRepository userRepository;

    public AuthDto.AuthResponse authenticate(AuthDto.AuthRequest authRequest) {
        UserDto userDto = userService.fetchUser(authRequest.getUsername());
        return authenticate(authRequest, userDto);
    }

    public AuthDto.AuthResponse register(AuthDto.RegisterRequest registerRequest) {
        UserDto userDto = userService.addUser(registerRequest);

        return this.authenticate(AuthDto.AuthRequest.builder()
                .username(registerRequest.getUsername())
                .password(registerRequest.getPassword())
                .build(), userDto);
    }

    public User getAuthenticatedUser () {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isEmpty()) throw new RuntimeException("Something is messed up in auth logic !!!");

        return user.get();
    }

    private AuthDto.AuthResponse authenticate(AuthDto.AuthRequest authRequest, UserDto userDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        String token = jwtUtil.generateToken(authentication);

        return AuthDto.AuthResponse.builder()
                .token(token)
                .userId(userDto.userId())
                .build();
    }
}
