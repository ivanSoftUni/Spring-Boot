package com.likebookapp.model.enums;

public enum MoodName {

    HAPPY("Happy"),
    SAD("Sad"),
    INSPIRED("Inspired");
    public final String label;

    MoodName(String label) {
        this.label = label;
    }
}
