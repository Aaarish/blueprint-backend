package com.roya.blueprint_backend.users;

import com.roya.blueprint_backend.auth.model.AuthDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto addUser(AuthDto.RegisterRequest registerRequest) {
        User user = User.builder()
                .userId(UUID.randomUUID().toString())
                .email(registerRequest.getEmail())
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .build();

        User savedUser = userRepository.save(user);

        return mapToUserDto(savedUser);
    }

    @Override
    public UserDto fetchUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return mapToUserDto(user);
    }

    private UserDto mapToUserDto(User user) {
        return UserDto.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .email(user.getEmail())
                .profileUrl(user.getProfileUrl())
                .build();
    }
}
