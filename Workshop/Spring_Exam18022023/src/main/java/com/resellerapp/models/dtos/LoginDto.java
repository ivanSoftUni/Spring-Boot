package com.resellerapp.models.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginDto {

    @Size(min = 3, max = 20, message ="Username length must be between 3 and 20 characters!")
    private String username;


    @Size(min = 3, max = 20, message ="Password length must be between 3 and 20 characters!" )
    private String password;

    public LoginDto(){

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
