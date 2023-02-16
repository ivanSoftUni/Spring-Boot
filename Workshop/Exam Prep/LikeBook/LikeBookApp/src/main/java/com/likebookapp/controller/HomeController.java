package com.likebookapp.controller;

import com.likebookapp.model.entity.Post;
import com.likebookapp.model.entity.User;
import com.likebookapp.service.AuthService;
import com.likebookapp.service.PostService;
import com.likebookapp.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;

@Controller
public class HomeController {

    private LoggedUser loggedUser;
    private PostService postService;
    private AuthService authService;

    public HomeController(LoggedUser loggedUser, PostService postService, AuthService authService) {
        this.loggedUser = loggedUser;
        this.postService = postService;
        this.authService = authService;
    }

    @GetMapping("/")
    public String index() {

        return "index";
    }

    @GetMapping("/home")
    public String getHome(Model model) {
        if (!this.loggedUser.isLogged()) {
            return "redirect:/";
        }

        User user = this.authService.findUserById(loggedUser.getId());
        model.addAttribute("currentUser", user);

        Set<Post> userPosts = this.postService.getUserPost();
        model.addAttribute("userPosts", userPosts);

        Set<Post> otherUsersPosts = this.postService.getOtherUsersPosts(loggedUser.getId());
        model.addAttribute("otherUsersPosts", otherUsersPosts);

        return "home";
    }


}
