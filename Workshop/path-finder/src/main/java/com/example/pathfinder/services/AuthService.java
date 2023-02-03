package com.example.pathfinder.services;

import com.example.pathfinder.models.dto.UserRegistrationDto;

public interface AuthService {

    void register(UserRegistrationDto registrationDto);

}
