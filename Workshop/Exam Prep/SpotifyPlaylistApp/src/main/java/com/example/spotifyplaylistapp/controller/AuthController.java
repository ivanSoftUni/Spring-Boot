package com.example.spotifyplaylistapp.controller;

import com.example.spotifyplaylistapp.model.dtos.LoginDto;
import com.example.spotifyplaylistapp.model.dtos.UserRegistrationDto;
import com.example.spotifyplaylistapp.service.UserService;
import com.example.spotifyplaylistapp.util.CurrentUser;
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
    private CurrentUser currentUser;

    public AuthController(UserService userService, CurrentUser currentUser) {
        this.userService = userService;
        this.currentUser = currentUser;
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
//        System.out.println(userRegistrationDto);

        return "redirect:/login";
    }


    @GetMapping("/login")
    public String login() {

        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid LoginDto loginDto,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("loginDto", loginDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.loginDTO", bindingResult);

            return "/login";
        }

        if (!this.userService.isLogin(loginDto)) {
            redirectAttributes.addFlashAttribute("loginDto", loginDto);
            redirectAttributes.addFlashAttribute("validCredentials", false);

            return "/login";
        }

        this.userService.login(loginDto);

        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout() {
        if (!this.userService.isLogged(currentUser)) {
            return "redirect:/login";
        }
        this.userService.logout(currentUser);
        return "redirect:/";
    }

}
