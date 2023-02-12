package com.softuni.battle_ships.services;

import com.softuni.battle_ships.models.dtos.LoginDto;
import com.softuni.battle_ships.models.dtos.UserRegistrationDto;
import com.softuni.battle_ships.models.entities.User;
import com.softuni.battle_ships.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private UserRepository userRepository;

    @Autowired
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public boolean register(UserRegistrationDto userRegistrationDto) {
        if (!userRegistrationDto.getPassword().equals(userRegistrationDto.getConfirmPassword())) {
            return false;
        }

        Optional<User> byEmail = this.userRepository.findByEmail(userRegistrationDto.getEmail());

        if (byEmail.isPresent()){
            return false;
        }

        Optional<User> byUsername = this.userRepository.findByUsername(userRegistrationDto.getUsername());

        if (byUsername.isPresent()){
            return false;
        }

        User user = new User();
        user.setUsername(userRegistrationDto.getUsername());
        user.setFullName(userRegistrationDto.getFullName());
        user.setEmail(userRegistrationDto.getEmail());
        user.setPassword(userRegistrationDto.getPassword());

        this.userRepository.save(user);

        return true;
    }

    public void logout() {

    }

    public boolean login(LoginDto loginDto) {
        return false;
    }
}
