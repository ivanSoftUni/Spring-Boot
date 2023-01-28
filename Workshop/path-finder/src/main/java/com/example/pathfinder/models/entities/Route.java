package com.example.pathfinder.models.entities;

import com.example.pathfinder.models.enums.Level;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "routes")
public class Route extends BaseEntity {

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "gpx_coordinates", columnDefinition = "LONGTEXT")
    private String gpxCoordinates;

    @Column(name = "level_enum")
    @Enumerated(EnumType.STRING)
    private Level level;

    @Column(nullable = false)
    private String name;

    @Column(name = "video_url")
    private String videoUrl;


    @ManyToOne
    private User author;

    @ManyToMany
    private Set<Category> categories;

    @OneToMany
    private Set<Comment> comments;

    @OneToMany(mappedBy = "route", fetch = FetchType.EAGER)
    private Set<Picture> pictures;

    public Route() {
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public Set<Picture> getPictures() {
        return pictures;
    }

    public String getGpxCoordinates() {
        return gpxCoordinates;
    }

    public void setGpxCoordinates(String gpxCoordinates) {
        this.gpxCoordinates = gpxCoordinates;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
