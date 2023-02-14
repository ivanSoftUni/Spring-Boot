package com.likebookapp.model.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AddPostDto {

    private Long id;

    @NotBlank
    @Size(min = 2, max = 150)
    private String content;

    @NotBlank
    private String mood;

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

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }
}
