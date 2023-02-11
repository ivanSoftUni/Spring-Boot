package com.softuni.battle_ships.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;

@Entity
@Table(name = "ships")
public class Ship extends BaseEntity {

    @NotBlank
    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    @Positive
    @NotBlank
    private long health;

    @Column(nullable = false)
    @Positive
    @NotBlank
    private long power;

    @Column(nullable = false)
    @NotBlank
    @Past
    private LocalDate created;

    @OneToOne
    private Category category;

    @OneToOne
    private User user;


    public Ship() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    public long getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
