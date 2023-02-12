package com.softuni.battle_ships.models.entities;

import com.softuni.battle_ships.models.enums.CategoryName;
import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    @Column(unique = true, nullable = false)
    private CategoryName name;


    @Column(columnDefinition = "TEXT")
    private String description;

    public Category() {

    }

    public Category(CategoryName name) {
        this.name = name;
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
