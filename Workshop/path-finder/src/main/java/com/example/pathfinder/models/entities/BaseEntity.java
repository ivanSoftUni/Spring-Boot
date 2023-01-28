package com.example.pathfinder.models.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity {
    @Id
//    @GeneratedValue(generator = "uuid-string")
//    @GenericGenerator(name = "uuid-string",
//            strategy = "org.hibernate.id.UUIDGenerator")

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
