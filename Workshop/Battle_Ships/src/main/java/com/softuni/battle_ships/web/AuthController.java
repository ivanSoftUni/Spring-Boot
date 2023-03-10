package com.softuni.battle_ships.web;

import com.softuni.battle_ships.models.dtos.LoginDto;
import com.softuni.battle_ships.models.dtos.UserRegistrationDto;
import com.softuni.battle_ships.repositories.UserRepository;
import com.softuni.battle_ships.services.AuthService;
import com.softuni.battle_ships.sesion.LoggedUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    private final UserRepository userRepository;
    private AuthService authService;
    private LoggedUser loggedUser;

    @Autowired
    public AuthController(UserRepository userRepository, AuthService authService, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.authService = authService;
        this.loggedUser = loggedUser;
    }

    @ModelAttribute("userRegistrationDto")
    public UserRegistrationDto initForm() {
        return new UserRegistrationDto();
    }

    @ModelAttribute("loginDto")
    public LoginDto initLogin() {
        return new LoginDto();
    }

    @GetMapping("/register")
    public String register() {

        return "register";
    }

    @PostMapping("/register")
    public String doRegister(@Valid UserRegistrationDto userRegistrationDto,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !this.authService.register(userRegistrationDto)) {
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.userRegistrationDto", bindingResult);
            redirectAttributes.addFlashAttribute("userRegistrationDto", userRegistrationDto);

            return "redirect:/register";
        }

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        if (loggedUser.getId() != null) {
            return "redirect:/home";
        }
        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid LoginDto loginDto,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("loginDto", loginDto);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.loginDto", bindingResult);

            return "redirect:/login";
        }
        if (!this.authService.login(loginDto)) {
            redirectAttributes.addFlashAttribute("loginDto", loginDto);
            redirectAttributes.addFlashAttribute("badCredentials", true);

            return "redirect:/login";
        }

        return "redirect:/home";
    }


    @GetMapping("/logout")
    public String logout() {


        return "redirect:/";

    }
}
