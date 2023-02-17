package com.softuni.coffeeshop.models.entities;

public enum CategoryType {

    COFFEE("Coffee"),
    CAKE("Cake"),
    DRINK("Drink"),
    OTHER("Other");

    private final String value;

    CategoryType(String value) {
        this.value = value;
    }

}
