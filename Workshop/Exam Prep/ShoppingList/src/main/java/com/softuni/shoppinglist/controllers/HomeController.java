package com.softuni.shoppinglist.controllers;

import com.softuni.shoppinglist.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


    private LoggedUser loggedUser;

    public HomeController(LoggedUser loggedUser) {
        this.loggedUser = loggedUser;
    }

    @GetMapping("/home")
    public String getHome(Model model){
        if (!this.loggedUser.isLogged()) {
            return "redirect:/";
        }




        return "home";
    }

}
