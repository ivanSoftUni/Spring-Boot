package com.likebookapp.controller;

import com.likebookapp.model.dtos.LoginUserDto;
import com.likebookapp.model.dtos.UserRegistrationDto;
import com.likebookapp.repository.UserRepository;
import com.likebookapp.service.AuthService;
import com.likebookapp.util.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class AuthController {

    private final UserRepository userRepository;

    private AuthService authService;
    private LoggedUser loggedUser;

    @ModelAttribute("userRegistrationDto")
    public UserRegistrationDto initForm() {
        return new UserRegistrationDto();
    }

    @ModelAttribute("loginUserDto")
    public LoginUserDto initLogin() {
        return new LoginUserDto();
    }

    @Autowired
    public AuthController(UserRepository userRepository, AuthService authService, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.authService = authService;
        this.loggedUser = loggedUser;
    }

    @GetMapping("/register")
    public String register() {

        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid UserRegistrationDto userRegistrationDto,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !authService.register(userRegistrationDto)) {
            redirectAttributes.addFlashAttribute("userRegistrationDto", userRegistrationDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationDto", bindingResult);

            return "redirect:/register";
        }

        System.out.println(userRegistrationDto);

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid LoginUserDto loginUserDto,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("loginUserDto", loginUserDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.loginUserDto", bindingResult);

            return "redirect:/login";
        }

        if (!authService.login(loginUserDto)) {
            redirectAttributes.addFlashAttribute("loginUserDto", loginUserDto);
            redirectAttributes.addFlashAttribute("validCredentials", true);

            return "redirect:/login";
        }
        return "redirect:/home";
    }

}
