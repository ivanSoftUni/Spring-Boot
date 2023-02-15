package com.example.spotifyplaylistapp.service;

import com.example.spotifyplaylistapp.model.dtos.SongAddDto;
import com.example.spotifyplaylistapp.model.entity.Song;
import com.example.spotifyplaylistapp.model.entity.Style;
import com.example.spotifyplaylistapp.model.entity.User;
import com.example.spotifyplaylistapp.model.entity.enums.StyleType;
import com.example.spotifyplaylistapp.repository.SongRepository;
import com.example.spotifyplaylistapp.repository.StyleRepository;
import com.example.spotifyplaylistapp.repository.UserRepository;
import com.example.spotifyplaylistapp.util.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongService {

    private SongRepository songRepository;
    private UserRepository userRepository;
    private StyleRepository styleRepository;
    private CurrentUser currentUser;

    @Autowired
    public SongService(SongRepository songRepository, UserRepository userRepository, StyleRepository styleRepository, CurrentUser currentUser) {
        this.songRepository = songRepository;
        this.userRepository = userRepository;
        this.styleRepository = styleRepository;
        this.currentUser = currentUser;
    }

    public boolean createSong(SongAddDto songAddDto) {

        Optional<Song> existSong = this.songRepository.findByTitle(songAddDto.getTitle());

        if (existSong.isPresent()) {
            return false;
        }

        Style style = this.styleRepository.findByName(StyleType.valueOf(songAddDto.getStyle().toUpperCase()));

        Song song = new Song();
        song.setPerformer(songAddDto.getPerformer());
        song.setTitle(songAddDto.getTitle());
        song.setReleaseDate(songAddDto.getReleaseDate());
        song.setDuration(songAddDto.getDuration());
        song.setStyle(style);

        System.out.println(song.toString());
        this.songRepository.save(song);

        return true;
    }

    public List<Song> getPopSongs() {
        Style style = this.styleRepository.findByName(StyleType.valueOf("POP"));
        List<Song> songs = this.songRepository.findAllByStyle(style);
        return songs;
    }

    public List<Song> getRockSongs() {
        Style style = this.styleRepository.findByName(StyleType.valueOf("ROCK"));
        List<Song> songs = this.songRepository.findAllByStyle(style);
        return songs;
    }

    public List<Song> getJazzSongs() {
        Style style = this.styleRepository.findByName(StyleType.valueOf("JAZZ"));
        List<Song> songs = this.songRepository.findAllByStyle(style);
        return songs;
    }

    public List<Song> getUserPlaylist() {
        Optional<User> byId = this.userRepository.findById(currentUser.getId());
        User user = byId.get();
        return user.getPlaylist();
    }
}
