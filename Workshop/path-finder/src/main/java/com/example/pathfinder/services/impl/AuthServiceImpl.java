package com.example.pathfinder.services.impl;

import com.example.pathfinder.models.dto.UserRegistrationDto;
import com.example.pathfinder.models.entities.User;
import com.example.pathfinder.repositories.UserRepository;
import com.example.pathfinder.services.AuthService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private UserRepository userRepository;

    @Override
    public void register(UserRegistrationDto registrationDto) {
        if (!registrationDto.getPassword().equals(registrationDto.getConfirmPassword())) {
            throw new RuntimeException("password.match");
        }

        Optional<User> byEmail = this.userRepository.findByEmail(registrationDto.getEmail());

        if (byEmail.isPresent()) {
            throw new RuntimeException("email.used");
        }

    }


}
