package com.example.pathfinder.models.entities;

import com.example.pathfinder.models.enums.CategoryName;
import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CategoryName name;

    @Column(columnDefinition = "LONGTEXT")
    private String description;

    @ManyToOne
    private Route route;

    public Category() {
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
