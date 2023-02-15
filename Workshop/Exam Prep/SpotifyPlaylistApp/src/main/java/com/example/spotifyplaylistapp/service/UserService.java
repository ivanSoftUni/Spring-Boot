package com.example.spotifyplaylistapp.service;

import com.example.spotifyplaylistapp.model.entity.Song;
import com.example.spotifyplaylistapp.repository.SongRepository;
import com.example.spotifyplaylistapp.util.CurrentUser;
import com.example.spotifyplaylistapp.model.dtos.LoginDto;
import com.example.spotifyplaylistapp.model.dtos.UserRegistrationDto;
import com.example.spotifyplaylistapp.model.entity.User;
import com.example.spotifyplaylistapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private CurrentUser currentUser;
    private SongRepository songRepository;


    @Autowired
    public UserService(UserRepository userRepository, CurrentUser currentUser, SongRepository songRepository) {
        this.userRepository = userRepository;
        this.currentUser = currentUser;
        this.songRepository = songRepository;
    }

    public boolean toRegister(UserRegistrationDto userRegistrationDto) {

        if (!userRegistrationDto.getPassword().equals(userRegistrationDto.getConfirmPassword())) {
            return false;
        }

        Optional<User> byUsername = this.userRepository.findByUsername(userRegistrationDto.getUsername());
        Optional<User> byEmail = this.userRepository.findByEmail(userRegistrationDto.getEmail());

        if (byUsername.isPresent() || byEmail.isPresent()) {
            return false;
        }

        User user = new User();
        user.setUsername(userRegistrationDto.getUsername());
        user.setEmail(userRegistrationDto.getEmail());
        user.setPassword(userRegistrationDto.getPassword());

        this.userRepository.save(user);
        return true;
    }

    public boolean isLogin(LoginDto loginDto) {

        Optional<User> user = this.userRepository.findByUsernameAndPassword(loginDto.getUsername(), loginDto.getPassword());

        if (user.isEmpty()) {
            return false;
        }

        return true;
    }

    public void login(LoginDto loginDto) {
        User user = this.userRepository.findByUsername(loginDto.getUsername()).get();

        this.currentUser.login(user);
    }

    public boolean isLogged() {

        return currentUser.getId() != null;
    }

    public void logout(CurrentUser currentUser) {
        this.currentUser.setId(null);
        this.currentUser.setUsername(null);
    }

    public void addToPlaylist(Long id) {

        Optional<Song> optionalSong = this.songRepository.findById(id);
        Song song = optionalSong.get();

        Optional<User> optionalUser = this.userRepository.findById(this.currentUser.getId());
        User user = optionalUser.get();
        user.getPlaylist().add(song);
        this.userRepository.save(user);
    }

    public String totalDurationOfPlaylist() {
        User user = this.userRepository.findById(currentUser.getId()).get();

        long playlistDuration = user.getPlaylist().stream().mapToLong(song -> song.getDuration()).sum();
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%d:%02d min. total", playlistDuration / 60, playlistDuration % 60));
        return sb.toString();
    }

    public void removeAllSongsFromPlaylist() {
        User user = this.userRepository.findById(currentUser.getId()).get();
        user.setPlaylist(new HashSet<>());
        this.userRepository.save(user);
    }
}
