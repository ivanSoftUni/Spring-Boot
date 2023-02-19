package com.resellerapp.controllers;

import com.resellerapp.models.dtos.LoginDto;
import com.resellerapp.models.dtos.UserRegistrationDto;

import com.resellerapp.services.UserService;
import com.resellerapp.util.LoggedUser;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {


    private UserService userService;
    private LoggedUser loggedUser;

    public AuthController(UserService userService, LoggedUser loggedUser) {
        this.userService = userService;
        this.loggedUser = loggedUser;
    }

    @ModelAttribute("userRegistrationDto")
    public UserRegistrationDto initForm() {
        return new UserRegistrationDto();
    }

    @ModelAttribute("loginDto")
    public LoginDto initLoginForm() {
        return new LoginDto();
    }


    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid UserRegistrationDto userRegistrationDto,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !this.userService.toRegister(userRegistrationDto)) {

            redirectAttributes.addFlashAttribute("userRegistrationDto", userRegistrationDto);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.userRegistrationDto", bindingResult);
            return "redirect:/register";
        }


        return "redirect:/login";
    }


    @GetMapping("/login")
    public String login() {

        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@Valid LoginDto loginDto,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("loginDto", loginDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.loginDTO", bindingResult);

            return "/login";
        }


        if (!this.userService.login(loginDto)) {
            redirectAttributes

                    .addFlashAttribute("validCredentials", false);

            return "redirect:/login";
        }
        this.userService.loginUser(loginDto.getUsername());

        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout() {
        if (!this.loggedUser.isLogged()) {
            return "redirect:/login";
        }
        this.userService.logout();
        return "redirect:/";
    }

}
