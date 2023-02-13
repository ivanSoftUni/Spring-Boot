package com.softuni.battle_ships.models.dtos;

import com.softuni.battle_ships.models.entities.Ship;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Positive;

public class ShipDto {

    private Long id;

    private String name;

    private long health;

    private long power;


    public ShipDto(Ship ship) {
        this.id = ship.getId();
        this.name = ship.getName();
        this.power = ship.getPower();
        this.health = ship.getHealth();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setHealth(long health) {
        this.health = health;
    }

    public long getPower() {
        return power;
    }

    public void setPower(long power) {
        this.power = power;
    }
}
