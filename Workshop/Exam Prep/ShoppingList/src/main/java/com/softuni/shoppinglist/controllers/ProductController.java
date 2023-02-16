package com.softuni.shoppinglist.controllers;

import com.softuni.shoppinglist.models.dtos.AddProductDto;
import com.softuni.shoppinglist.services.ProductService;
import com.softuni.shoppinglist.util.LoggedUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProductController {

    private ProductService productService;
    private LoggedUser loggedUser;

    @Autowired
    public ProductController(ProductService productService, LoggedUser loggedUser) {
        this.productService = productService;
        this.loggedUser = loggedUser;
    }

    @ModelAttribute("addProductDto")
    public AddProductDto initForm() {
        return new AddProductDto();
    }


    @GetMapping("/product/add")
    public String add() {
        if (!this.loggedUser.isLogged()) {
            return "redirect:/";
        }
        return "product-add";
    }

    @PostMapping("/product/add")
    public String add(@Valid AddProductDto addProductDto,
                      BindingResult bindingResult,
                      RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !this.productService.registerProduct(addProductDto)) {
            redirectAttributes.addFlashAttribute("addProductDto", addProductDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addProductDto", bindingResult);

            return "redirect:/product/add";
        }


        return "redirect:/home";
    }
}
