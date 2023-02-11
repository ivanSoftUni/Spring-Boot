package com.softuni.battle_ships.models.dto;

import jakarta.validation.constraints.*;


public class UserRegistrationDto {
    @NotBlank
    @Size(min = 3, max = 10)
    private String username;

    @NotBlank
    @Size(min = 5, max = 20)
    private String fullName;

    @NotBlank
    @Min(3)
    private String password;

    @NotBlank

    @Email
    private String email;

    public UserRegistrationDto(){

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
