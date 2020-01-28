package com.example.demo.service;

import com.example.demo.dto.model.user.UserDto;

public interface UserService {

    // Register a new user
    UserDto signUp(UserDto userDto);


    // Search an existing user
    UserDto findUserByEmail(String email);


    // Update profile of the user
    UserDto updateProfile(UserDto userDto);


    // Update password
    UserDto changePassword(UserDto userDto, String newPassword);
}
