package com.example.spotifyplaylistapp.model.dtos;

import com.example.spotifyplaylistapp.model.entity.Style;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class SongAddDto {
    private Long id;
    @NotNull
    @Size(min = 3, max = 20)
    private String performer;

    @NotNull
    @Size(min = 2, max = 20)
    private String title;

    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;

    @NotNull
    @Positive
    private Long duration;

    @NotNull
    private String style;

    public SongAddDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPerformer() {
        return performer;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String name) {
        this.style = name;
    }
}
