package com.softuni.shoppinglist.models.dtos;

import com.softuni.shoppinglist.models.entities.CategoryType;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AddProductDto {
    @NotNull
    @Size(min = 3, max = 20)
    private String name;
    @NotNull
    @Size(min = 5)
    private String description;
    @NotNull
    @FutureOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime before;

    @NotNull
    @Positive
    private BigDecimal price;

    @NotNull
    private CategoryType category;


    public AddProductDto() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getBefore() {
        return before;
    }

    public void setBefore(LocalDateTime before) {
        this.before = before;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public CategoryType getCategory() {
        return category;
    }

    public void setCategory(CategoryType category) {
        this.category = category;
    }
}
