package com.resellerapp.controllers;

import com.resellerapp.models.entities.Offer;
import com.resellerapp.services.OfferService;
import com.resellerapp.services.UserService;
import com.resellerapp.util.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Set;

@Controller
public class HomeController {

    private final LoggedUser loggedUser;
    private UserService userService;
    private OfferService offerService;


    @Autowired
    public HomeController(LoggedUser loggedUser, UserService userService, OfferService offerService) {
        this.loggedUser = loggedUser;
        this.userService = userService;
        this.offerService = offerService;
    }

    @GetMapping("/home")
    public String getHome(Model model) {
        if (!this.loggedUser.isLogged()) {
            return "redirect:/";
        }

        model.addAttribute("loggedUser", loggedUser);
        List<Offer> currentUserOffers = this.userService.getCurrentUserOffers();
        model.addAttribute("currentUserOffers", currentUserOffers);

        Set<Offer> currentUserBoughtOffers = this.userService.getCurrentUserBoughtOffers();
        model.addAttribute("currentUserBoughtOffers", currentUserBoughtOffers);

        List<Offer> offerWithOutBuyer = this.offerService.getOfferwithOutBuyer(loggedUser.getId());
        model.addAttribute("othersUsersOffers", offerWithOutBuyer);



        return "home";
    }

    @GetMapping("/remove/{id}")
    public String removeUserOffer(@PathVariable Long id) {
        this.offerService.removeOffer(id);

        return "redirect:/home";
    }

    @GetMapping("/buy/{id}")
    public String buyOfferFromUser(@PathVariable Long id) {

        this.offerService.buyOffer(id);

        return "redirect:/home";
    }

}
