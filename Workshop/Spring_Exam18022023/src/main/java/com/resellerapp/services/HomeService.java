package com.resellerapp.services;

import com.resellerapp.util.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HomeService {

    private UserService userService;
    private LoggedUser loggedUser;

    private OfferService offerService;

    @Autowired
    public HomeService(UserService userService, LoggedUser loggedUser, OfferService offerService) {
        this.userService = userService;
        this.loggedUser = loggedUser;
        this.offerService = offerService;
    }



}
