package com.softuni.shoppinglist.models.entities;

public enum CategoryType {

    FOOD("Food"),
    DRINK("Drink"),
    HOUSEHOLD("Household"),
    OTHER("Other");

    private final String name;

    private CategoryType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
