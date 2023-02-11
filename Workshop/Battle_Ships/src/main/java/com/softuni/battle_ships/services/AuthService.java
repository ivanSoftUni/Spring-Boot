package com.softuni.battle_ships.services;

import com.softuni.battle_ships.models.dto.UserRegistrationDto;
import com.softuni.battle_ships.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private UserRepository userRepository;

    @Autowired
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public void register(UserRegistrationDto userRegistrationDto) {


    }

    public void logout() {

    }
}
