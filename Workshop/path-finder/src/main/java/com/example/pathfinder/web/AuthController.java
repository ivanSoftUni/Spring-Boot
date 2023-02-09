package com.example.pathfinder.web;

import com.example.pathfinder.models.dto.UserRegistrationDto;
import com.example.pathfinder.repositories.UserRepository;
import com.example.pathfinder.services.AuthService;
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

    @Autowired
    public AuthController(UserRepository userRepository, AuthService authService) {
        this.userRepository = userRepository;
        this.authService = authService;
    }

    @ModelAttribute("userRegistrationDto")
    public UserRegistrationDto initForm() {
        return new UserRegistrationDto();
    }

    @GetMapping("/register")
    public String register() {

        return "register";
    }

    @PostMapping("/register")
    public String doRegister(@Valid UserRegistrationDto userRegistrationDto,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationDto", bindingResult);
            redirectAttributes.addFlashAttribute("userRegistrationDto", userRegistrationDto);

            return "redirect:/register";
        }
        this.authService.register(userRegistrationDto);

//        System.out.println(userRegistrationDto.toString());
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        this.authService.logout();

        return "redirect:/";

    }


}
