package com.roya.blueprint_backend.users;

import com.roya.blueprint_backend.auth.model.AuthDto;

public interface UserService {
    UserDto addUser (AuthDto.RegisterRequest registerRequest);
    UserDto fetchUser(String username);

}
