package com.softuni.battle_ships.models.entities;

import com.softuni.battle_ships.models.entities.enums.CategoryName;
import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

    @Column(unique = true, nullable = false)
    @Enumerated
    private CategoryName name;

    @Column(columnDefinition = "TEXT")
    private String description;

    public Category() {

    }

    public CategoryName getName() {
        return name;
    }

    public void setName(CategoryName name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
