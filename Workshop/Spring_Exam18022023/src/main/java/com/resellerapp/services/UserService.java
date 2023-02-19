package com.resellerapp.services;


import com.resellerapp.models.dtos.LoginDto;
import com.resellerapp.models.dtos.UserRegistrationDto;
import com.resellerapp.models.entities.Offer;
import com.resellerapp.models.entities.User;
import com.resellerapp.repositories.UserRepository;
import com.resellerapp.util.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    private final UserRepository userRepository;
    private LoggedUser loggedUser;


    @Autowired
    public UserService(UserRepository userRepository, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
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


    public void logout() {
        this.loggedUser.setId(null);
        this.loggedUser.setUsername(null);
    }


    public void addOfferToUser(User user, Offer offer) {
        user.getOffers().add(offer);
        this.userRepository.save(user);
    }

    public List<Offer> getCurrentUserOffers() {
        return this.userRepository.findByUsername(loggedUser.getUsername()).get().getOffers();
    }

    public Set<Offer> getCurrentUserBoughtOffers() {
        return this.userRepository.findByUsername(loggedUser.getUsername()).get().getBoughtOffers();
    }


    public User getLoginUser(Long id) {

        return this.userRepository.findById(id).orElse(null);
    }
}
