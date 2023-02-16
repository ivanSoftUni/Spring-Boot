package com.softuni.shoppinglist.services;

import com.softuni.shoppinglist.models.dtos.LoginUserDto;
import com.softuni.shoppinglist.models.dtos.UserRegistrationDto;
import com.softuni.shoppinglist.models.entities.User;
import com.softuni.shoppinglist.repositories.UserRepository;
import com.softuni.shoppinglist.util.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {


    private UserRepository userRepository;
    private LoggedUser loggedUser;

    @Autowired
    public AuthService(UserRepository userRepository, LoggedUser loggedUser) {
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
        Optional<User> userById = this.userRepository.findByUsernameAndPassword(
                loginUserDto.getUsername(),
                loginUserDto.getPassword());

        if (userById.isEmpty()) {
            return false;
        }

        return true;

    }

    public void loginUser(String username) {
        User user = this.getUserByUsername(username);

        this.loggedUser.setId(user.getId());
        this.loggedUser.setUsername(user.getUsername());
    }

    private User getUserByUsername(String username) {
        return this.userRepository.findByUsername(username).orElse(null);
    }

    public void logout() {
        this.loggedUser.setId(null);
        this.loggedUser.setUsername(null);
    }
}
