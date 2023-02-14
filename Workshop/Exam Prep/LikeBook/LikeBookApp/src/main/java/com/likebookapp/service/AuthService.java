package com.likebookapp.service;

import com.likebookapp.model.dtos.LoginUserDto;
import com.likebookapp.model.dtos.UserRegistrationDto;
import com.likebookapp.model.entity.User;
import com.likebookapp.repository.UserRepository;
import com.likebookapp.util.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private UserRepository userRepository;
    private LoggedUser loggedUser;


    @Autowired
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
    }

    public boolean register(UserRegistrationDto userRegistrationDto) {

        if (!userRegistrationDto.getPassword().equals(userRegistrationDto.getConfirmPassword())) {
            return false;
        }

        Optional<User> byEmail = this.userRepository.findByEmail(userRegistrationDto.getEmail());

        if (byEmail.isPresent()) {
            return false;
        }

        Optional<User> byUsername = this.userRepository.findByUsername(userRegistrationDto.getUsername());

        if (byUsername.isPresent()) {
            return false;
        }

        User user = new User();
        user.setUsername(userRegistrationDto.getUsername());
        user.setEmail(userRegistrationDto.getEmail());
        user.setPassword(userRegistrationDto.getPassword());

        this.userRepository.save(user);

        return true;
    }

    public boolean login(LoginUserDto loginUserDto) {

        Optional<User> user = this.userRepository.findByUsernameAndPassword(
                loginUserDto.getUsername(),
                loginUserDto.getPassword());

        if (user.isEmpty()) {
            return false;
        }

        this.loggedUser.login(user.get());

        return true;
    }
}
