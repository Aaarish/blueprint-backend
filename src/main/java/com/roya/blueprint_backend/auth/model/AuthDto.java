package com.roya.blueprint_backend.auth.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

public class AuthDto {
    @Data
    @Builder
    public static class AuthRequest {
        private String username;
        private String password;
    }

    @Data
    @Builder
    public static class RegisterRequest {
        private String email;
        private String username;
        private String password;
//        private MultipartFile profile;
    }

    @Data
    @Builder
    public static class AuthResponse {
        private String token;
        private String userId;
    }

}
