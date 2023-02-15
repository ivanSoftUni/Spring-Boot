package com.example.spotifyplaylistapp.controller;

import com.example.spotifyplaylistapp.model.dtos.SongAddDto;
import com.example.spotifyplaylistapp.repository.SongRepository;
import com.example.spotifyplaylistapp.service.SongService;
import com.example.spotifyplaylistapp.service.UserService;
import com.example.spotifyplaylistapp.util.CurrentUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SongController {

    private SongRepository songRepository;
    private SongService songService;
    private UserService userService;
    private CurrentUser currentUser;

    @Autowired
    public SongController(SongRepository songRepository, SongService service, SongService songService, UserService userService, CurrentUser currentUser) {
        this.songRepository = songRepository;
        this.songService = songService;
        this.userService = userService;
        this.currentUser = currentUser;
    }


    @ModelAttribute("songAddDto")
    public SongAddDto initSongForm() {
        return new SongAddDto();
    }

    @GetMapping("/song/add")
    public String addSong() {
        if (!this.userService.isLogged(currentUser)) {
            return "redirect:/";
        }
        return "song-add";
    }

    @PostMapping("/song/add")
    public String addSong(@Valid SongAddDto songAddDto,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !this.songService.createSong(songAddDto)) {
            redirectAttributes.addFlashAttribute("songAddDto", songAddDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.songAddDto", bindingResult);

            return "redirect:/song/add";
        }


        return "redirect:/home";
    }

}
