package com.resellerapp.models.entities;

import com.resellerapp.models.enums.ConditionType;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "conditions")
public class Condition extends BaseEntity {

    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private ConditionType name;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "condition")
    private List<Offer> offers;

    public Condition(ConditionType name) {
        this.name = name;
    }

    public Condition() {

    }

    public ConditionType getName() {
        return name;
    }

    public void setName(ConditionType name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
