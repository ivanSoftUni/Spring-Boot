package com.likebookapp.model.entity;

import com.likebookapp.model.enums.MoodName;

import javax.persistence.*;

@Entity
@Table(name = "moods")
public class Mood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private MoodName name;

    @Column
    private String description;

    public Mood() {

    }

    public Mood(MoodName name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MoodName getName() {
        return name;
    }

    public void setName(MoodName name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
