package com.example.pathfinder.web;

import com.example.pathfinder.models.entities.Route;
import com.example.pathfinder.services.impl.RouteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final RouteServiceImpl routeService;

    @Autowired
    public HomeController(RouteServiceImpl routeService) {
        this.routeService = routeService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Route> routes = routeService.getMostCommented();

        model.addAttribute("mostCommented", routes.get(0));

        return "index";
    }

}
