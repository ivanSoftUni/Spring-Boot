package com.likebookapp.controller;

import com.likebookapp.model.dtos.AddPostDto;
import com.likebookapp.service.PostService;
import com.likebookapp.util.LoggedUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@Controller
public class PostController {

    private PostService postService;
    private LoggedUser loggedUser;

    @Autowired
    public PostController( PostService postService, LoggedUser loggedUser) {
        this.postService = postService;
        this.loggedUser = loggedUser;
    }

    @ModelAttribute("addPostDto")
    public AddPostDto initAddPost() {
        return new AddPostDto();
    }


    @GetMapping("/post/add")
    public String addPost() {
        if (!this.loggedUser.isLogged()){
            return "redirect:/";
        }
        return "post-add";
    }


    @PostMapping("/post/add")
    public String addPost(@Valid AddPostDto addPostDto,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !this.postService.registerPost(addPostDto)) {
            redirectAttributes.addFlashAttribute("addPostDto", addPostDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addPostDto", bindingResult);

            return "redirect:/post/add";
        }

        return "redirect:/home";
    }

    @GetMapping("/remove/{id}")
    public String removeUserPost(@PathVariable Long id){

        this.postService.removePost(id);

        return "redirect:/home";
    }

    @GetMapping("/likes/{id}")
    public String likePost(@PathVariable Long id){

        this.postService.addUserLikes(id);


        return "redirect:/home";
    }
}
