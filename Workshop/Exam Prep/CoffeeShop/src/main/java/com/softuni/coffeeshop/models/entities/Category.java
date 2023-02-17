package com.softuni.coffeeshop.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private CategoryType name;

    @Column(nullable = false)
    private Integer needTime;

    public Category(CategoryType categoryType) {
        this.name = categoryType;
    }

    public Category() {

    }

    public CategoryType getName() {
        return name;
    }

    public void setName(CategoryType name) {
        this.name = name;
    }

    public Integer getNeedTime() {
        return needTime;
    }

    public void setNeedTime(Integer needTime) {
        this.needTime = needTime;
    }
}
