package com.softuni.shoppinglist.controllers;

import com.softuni.shoppinglist.models.entities.Product;
import com.softuni.shoppinglist.services.ProductService;
import com.softuni.shoppinglist.util.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Controller
public class HomeController {

    private ProductService productService;
    private LoggedUser loggedUser;

    @Autowired
    public HomeController(ProductService productService, LoggedUser loggedUser) {
        this.productService = productService;
        this.loggedUser = loggedUser;
    }

    @GetMapping("/home")
    public String getHome(Model model) {
        if (!this.loggedUser.isLogged()) {
            return "redirect:/";
        }

        Set<Product> foods = this.productService.getProducts("FOOD");
        model.addAttribute("AllFoods", foods);

        Set<Product> households = this.productService.getProducts("HOUSEHOLD");
        model.addAttribute("households", households);

        Set<Product> drinks = this.productService.getProducts("DRINK");
        model.addAttribute("drinks", drinks);

        Set<Product> otherProducts = this.productService.getProducts("OTHER");
        model.addAttribute("otherProducts", otherProducts);

        List<Product> AllProducts = this.productService.getAllProducts();
        BigDecimal totalPrice = this.productService.getTotalPrice();
        model.addAttribute("totalPrice", totalPrice);


        return "home";
    }

    @GetMapping("/remove-All-Products")
    public String removeAll(){
        if (!loggedUser.isLogged()) {
            return "redirect:/login";
        }

        this.productService.removeAllProducts();
        return "home";
    }

}
