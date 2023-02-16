package com.likebookapp.model.dtos;

import com.likebookapp.model.enums.MoodName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class AddPostDto {

    private Long id;


    @NotNull
    @Size(min = 2, max = 150)
    private String content;

    @NotNull
    private MoodName mood;

    public AddPostDto() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MoodName getMood() {
        return mood;
    }

    public void setMood(MoodName mood) {
        this.mood = mood;
    }
}
