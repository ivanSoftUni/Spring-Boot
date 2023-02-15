package com.example.spotifyplaylistapp.service;

import com.example.spotifyplaylistapp.model.dtos.SongAddDto;
import com.example.spotifyplaylistapp.model.entity.Song;
import com.example.spotifyplaylistapp.model.entity.Style;
import com.example.spotifyplaylistapp.model.entity.enums.StyleType;
import com.example.spotifyplaylistapp.repository.SongRepository;
import com.example.spotifyplaylistapp.repository.StyleRepository;
import com.example.spotifyplaylistapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SongService {

    private SongRepository songRepository;
    private UserRepository userRepository;
    private StyleRepository styleRepository;

    @Autowired
    public SongService(SongRepository songRepository, UserRepository userRepository, StyleRepository styleRepository) {
        this.songRepository = songRepository;
        this.userRepository = userRepository;
        this.styleRepository = styleRepository;
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

        this.songRepository.save(song);

        return true;
    }

}
