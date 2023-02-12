package com.softuni.battle_ships.models.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginDto {

    @NotBlank
    @Size(min = 3, max = 10)
    private String username;


    @NotBlank
    @Size(min = 3)
    private String password;

    public LoginDto() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
