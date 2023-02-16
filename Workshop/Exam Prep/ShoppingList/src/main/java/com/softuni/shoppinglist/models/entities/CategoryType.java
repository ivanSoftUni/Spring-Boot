package com.softuni.shoppinglist.models.entities;

public enum CategoryType {

    FOOD("Food"),
    DRINK("Drink"),
    HOUSEHOLD("Household"),
    OTHER("Other");

    private final String value;

    private CategoryType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
