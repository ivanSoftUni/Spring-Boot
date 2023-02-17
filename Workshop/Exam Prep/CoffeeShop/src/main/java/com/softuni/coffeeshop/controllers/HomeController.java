package com.softuni.coffeeshop.controllers;

import com.softuni.coffeeshop.models.entities.Order;
import com.softuni.coffeeshop.models.entities.User;
import com.softuni.coffeeshop.services.OrderService;
import com.softuni.coffeeshop.services.UserService;
import com.softuni.coffeeshop.util.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class HomeController {
    private OrderService orderService;
    private LoggedUser loggedUser;
    private UserService userService;

    @Autowired
    public HomeController(OrderService orderService, LoggedUser loggedUser, UserService userService) {
        this.orderService = orderService;
        this.loggedUser = loggedUser;
        this.userService = userService;
    }

    @GetMapping("/home")
    public String getHome(Model model) {
        if (!this.loggedUser.isLogged()) {
            return "redirect:/";
        }

        List<Order> allOrders = this.orderService.getOrders();
        model.addAttribute("allOrders", allOrders);

        List<User> employees = this.orderService.getUsersWithOrders();
        model.addAttribute("employees", employees);



//        List<Order> coffees = this.orderService.getOrders(CategoryType.COFFEE);
//        model.addAttribute("coffees", coffees);
//
//        List<Order> drinks = this.orderService.getOrders(CategoryType.DRINK);
//        model.addAttribute("drinks", drinks);
//
//        List<Order> others = this.orderService.getOrders(CategoryType.OTHER);
//        model.addAttribute("others", others);


        return "home";
    }
}
