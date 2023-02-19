package com.resellerapp.models.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "offers")
public class Offer extends BaseEntity {

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Double price;

    @ManyToOne
    private Condition condition;

    @ManyToOne
    private User user;

    @ManyToOne
    private User buyers;

    public Offer() {

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getBuyers() {
        return buyers;
    }

    public void setBuyers(User buyers) {
        this.buyers = buyers;
    }
}
