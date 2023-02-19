package com.resellerapp.controllers;

import com.resellerapp.models.dtos.AddOfferDto;
import com.resellerapp.services.OfferService;
import com.resellerapp.util.LoggedUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class OfferController {

    private final LoggedUser loggedUser;
    private OfferService offerService;

    @Autowired
    public OfferController(LoggedUser loggedUser, OfferService offerService) {
        this.loggedUser = loggedUser;
        this.offerService = offerService;
    }

    @ModelAttribute("addOfferDto")
    public AddOfferDto initForm() {
        return new AddOfferDto();
    }

    @GetMapping("/offer/add")
    public String offer() {
        if (!this.loggedUser.isLogged()) {
            return "redirect:/";
        }
        return "offer-add";
    }

    @PostMapping("/offer/add")

    public String addOrder(@Valid AddOfferDto addOfferDto,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {
        if (!this.loggedUser.isLogged()) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors() || !this.offerService.registerOffer(addOfferDto)) {
            redirectAttributes.addFlashAttribute("addOfferDto", addOfferDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addOfferDto", bindingResult);

            return "redirect:/offer/add";
        }


        return "redirect:/home";
    }

}
