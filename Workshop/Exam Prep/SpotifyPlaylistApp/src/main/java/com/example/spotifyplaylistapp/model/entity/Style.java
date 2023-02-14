package com.example.spotifyplaylistapp.model.entity;

import com.example.spotifyplaylistapp.model.entity.enums.StyleType;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "styles")
public class Style extends BaseEntity {
    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private StyleType name;


    @Column
    private String description;

    @OneToMany
    private List<Song> songs = new ArrayList<>();

    public Style() {

    }

    public Style(StyleType name) {
        this.name = name;
    }

    public StyleType getName() {
        return name;
    }

    public void setName(StyleType name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
