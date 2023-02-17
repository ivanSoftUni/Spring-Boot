package com.softuni.coffeeshop.services;

import com.softuni.coffeeshop.models.dtos.LoginDto;
import com.softuni.coffeeshop.models.dtos.UserRegistrationDto;
import com.softuni.coffeeshop.models.entities.User;
import com.softuni.coffeeshop.repositories.UserRepository;
import com.softuni.coffeeshop.util.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private LoggedUser loggedUser;
    private OrderService orderService;


    @Autowired
    public UserService(UserRepository userRepository, LoggedUser loggedUser, OrderService orderService) {
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
        this.orderService = orderService;
    }

    public boolean toRegister(UserRegistrationDto userRegistrationDto) {

        if (!userRegistrationDto.getPassword().equals(userRegistrationDto.getConfirmPassword())) {
            return false;
        }

        Optional<User> byUsername = this.userRepository.findByUsername(userRegistrationDto.getUsername());
        Optional<User> byEmail = this.userRepository.findByEmail(userRegistrationDto.getEmail());

        if (byUsername.isPresent() || byEmail.isPresent()) {
            return false;
        }

        User user = new User();
        user.setUsername(userRegistrationDto.getUsername());
        user.setFirstName(userRegistrationDto.getFirstName());
        user.setLastName(userRegistrationDto.getLastName());
        user.setEmail(userRegistrationDto.getEmail());
        user.setPassword(userRegistrationDto.getPassword());

        this.userRepository.save(user);
        return true;
    }

    public boolean login(LoginDto loginUserDto) {
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

//    public boolean isLogged() {
//        return loggedUser.getId() != null;
//    }

    public void logout() {
        this.loggedUser.setId(null);
        this.loggedUser.setUsername(null);
    }


}
