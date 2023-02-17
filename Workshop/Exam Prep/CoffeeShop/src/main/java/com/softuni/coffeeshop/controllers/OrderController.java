package com.softuni.coffeeshop.controllers;

import com.softuni.coffeeshop.models.dtos.AddOrderDto;
import com.softuni.coffeeshop.services.OrderService;
import com.softuni.coffeeshop.util.LoggedUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class OrderController {

    private LoggedUser loggedUser;
    private OrderService orderService;

    @Autowired
    public OrderController(LoggedUser loggedUser, OrderService orderService) {
        this.loggedUser = loggedUser;
        this.orderService = orderService;
    }

    @ModelAttribute("addOrderDto")
    public AddOrderDto initForm() {
        return new AddOrderDto();
    }

    @GetMapping("/order/add")
    public String order() {
        if (!this.loggedUser.isLogged()) {
            return "redirect:/";
        }
        return "order-add";
    }


    @PostMapping("/order/add")
    public String addOrder(@Valid AddOrderDto addOrderDto,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !this.orderService.registerOrder(addOrderDto)) {
            redirectAttributes.addFlashAttribute("addOrderDto", addOrderDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addOrderDto", bindingResult);

            return "redirect:/order/add";
        }


        return "redirect:/home";
    }

}
