package com.likebookapp.controller;

import com.likebookapp.model.dtos.AddPostDto;
import com.likebookapp.service.PostService;
import com.likebookapp.session.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(LoggedUser loggedUser, PostService postService) {
        this.postService = postService;
    }

    @ModelAttribute("addPostDto")
    public AddPostDto initAddPost() {
        return new AddPostDto();
    }


    @GetMapping("/post/add")
    public String addPost() {

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
}
