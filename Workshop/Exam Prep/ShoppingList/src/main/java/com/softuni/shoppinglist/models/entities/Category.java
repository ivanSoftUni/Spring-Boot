package com.softuni.shoppinglist.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private CategoryType name;

    @Column
    private String description;

    public Category(CategoryType name) {
        this.name = name;
    }

    public Category() {

    }

    public CategoryType getName() {
        return name;
    }

    public void setName(CategoryType name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
