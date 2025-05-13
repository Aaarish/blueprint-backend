package com.roya.blueprint_backend.users;

import com.roya.blueprint_backend.common.Category;
import lombok.Builder;

import java.util.List;

@Builder
public record UserDto (
        String userId,
        String username,
        String email,
        String password,
        String profileUrl,
        String linkedinUrl,
        List<Category>interests
) {}
