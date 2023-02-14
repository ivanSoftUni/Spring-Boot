package com.example.spotifyplaylistapp.util;

import com.example.spotifyplaylistapp.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class CurrentUser {

    private Long id;

    private String username;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void login(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
    }
}
