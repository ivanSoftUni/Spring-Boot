package com.example.spotifyplaylistapp.controller;

import com.example.spotifyplaylistapp.model.entity.Song;
import com.example.spotifyplaylistapp.service.SongService;
import com.example.spotifyplaylistapp.service.UserService;
import com.example.spotifyplaylistapp.util.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    private UserService userService;
    private CurrentUser currentUser;
    private SongService songService;


    public HomeController(UserService userService, CurrentUser currentUser, SongService songService) {
        this.userService = userService;
        this.currentUser = currentUser;
        this.songService = songService;
    }


    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/home")
    public String getHome(Model model) {

        if (!userService.isLogged()) {
            return "redirect:/";
        }

        List<Song> popSongs = this.songService.getPopSongs();
        model.addAttribute("popSongs", popSongs);

        List<Song> rockSongs = this.songService.getRockSongs();
        model.addAttribute("rockSongs", rockSongs);

        List<Song> jazzSongs = this.songService.getJazzSongs();
        model.addAttribute("jazzSongs", jazzSongs);

        List<Song> userPlaylist = this.songService.getUserPlaylist();
        model.addAttribute("userPlaylist", userPlaylist);

        return "home";
    }


}
